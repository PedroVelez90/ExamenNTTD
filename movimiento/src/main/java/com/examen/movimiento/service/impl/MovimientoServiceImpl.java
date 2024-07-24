package com.examen.movimiento.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.examen.movimiento.exception.FinancieroException;
import com.examen.movimiento.repository.CuentaRepository;
import com.examen.movimiento.repository.MovimientoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.examen.movimiento.dto.MovimientoDto;
import com.examen.movimiento.dto.ClienteDto;
import com.examen.movimiento.dto.EstadoCuentaDto;
import com.examen.movimiento.dto.MovimientoRequestDto;
import com.examen.movimiento.entity.Cuenta;
import com.examen.movimiento.entity.Movimiento;
import com.examen.movimiento.enums.TipoMovimiento;
import com.examen.movimiento.service.ClienteService;
import com.examen.movimiento.service.MovimientoService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    private final ModelMapper modelMapper;

    private final CuentaRepository cuentaRepo;

    private final MovimientoRepository movimientoRepo;

    private final ClienteService clienteServ;

    @Override
    public MovimientoDto guardar(MovimientoRequestDto movimientoReq) {
        Movimiento movimientoEntity = modelMapper.map(movimientoReq, Movimiento.class);
        log.info(movimientoReq.getTipoCuenta());

        Cuenta cuenta = obtenerCuentaActiva(movimientoReq);
        movimientoEntity.setCuenta(cuenta);

        validarTipoMovimiento(movimientoEntity);

        Double nuevoSaldo = calcularNuevoSaldo(cuenta, movimientoEntity);

        movimientoEntity.setSaldo(nuevoSaldo);

        Movimiento movimientoNuevo = movimientoRepo.save(movimientoEntity);

        return modelMapper.map(movimientoNuevo, MovimientoDto.class);
    }

    private Cuenta obtenerCuentaActiva(MovimientoRequestDto movimientoReq) {
        return cuentaRepo
                .findByNumeroCuentaAndTipoCuenta(movimientoReq.getNumeroCuenta(), movimientoReq.getTipoCuenta())
                .filter(Cuenta::isEstado)
                .orElseThrow(() -> new FinancieroException("La cuenta no existe"));
    }

    private void validarTipoMovimiento(Movimiento movimientoEntity) {
        if (!movimientoEntity.getTipoMovimiento().equals(TipoMovimiento.RETIRO.name())
                && !movimientoEntity.getTipoMovimiento().equals(TipoMovimiento.DEPOSITO.name())) {
            throw new FinancieroException("Tipo Movimiento no existe");
        }
    }

    private Double calcularNuevoSaldo(Cuenta cuenta, Movimiento movimientoEntity) {
        Optional<Movimiento> movimientoOpt = movimientoRepo.findFirstByCuentaOrderByFechaDesc(cuenta);

        if (movimientoOpt.isPresent()) {
            Movimiento ultimoMovimiento = movimientoOpt.get();
            if (ultimoMovimiento.getSaldo() == 0
                    && movimientoEntity.getTipoMovimiento().equals(TipoMovimiento.RETIRO.name())) {
                throw new FinancieroException("Saldo no disponible");
            }
            if (movimientoEntity.getTipoMovimiento().equals(TipoMovimiento.RETIRO.name())) {
                Double nuevoSaldo = ultimoMovimiento.getSaldo() - movimientoEntity.getValor();
                if (nuevoSaldo < 0) {
                    throw new FinancieroException("Saldo insuficiente para la transacción");
                }
                return nuevoSaldo;
            }
            if (movimientoEntity.getTipoMovimiento().equals(TipoMovimiento.DEPOSITO.name())) {
                return ultimoMovimiento.getSaldo() + movimientoEntity.getValor();
            }
        }
        return movimientoEntity.getValor();
    }

    @Override
    public List<EstadoCuentaDto> consultarEstadoCuenta(String identificacion, Date fechaInicio, Date fechaFin) {
        ClienteDto clienteDto = clienteServ.consultarCliente(identificacion);

        //List<Movimiento> movimientos = movimientoRepo.findAllByCuentaClienteAndFechaBetween(clienteDto.getIdentificacion(), fechaInicio,
          //      fechaFin);
        return mapearEstadoCuenta(null, clienteDto);
    }

    private List<EstadoCuentaDto> mapearEstadoCuenta(List<Movimiento> movimientos, ClienteDto clienteDto) {
        return movimientos.stream()
                .map(movimiento -> {
                    EstadoCuentaDto estadoCuentaDto = new EstadoCuentaDto();
                    estadoCuentaDto.setFecha(movimiento.getFecha());
                    estadoCuentaDto.setCliente(clienteDto.getResultado().getNombre());
                    estadoCuentaDto.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
                    estadoCuentaDto.setTipo(movimiento.getCuenta().getTipoCuenta());
                    estadoCuentaDto.setSaldoInicial(obtenerSaldoInicial(movimiento));
                    estadoCuentaDto.setEstado(movimiento.getCuenta().isEstado());
                    estadoCuentaDto.setMovimiento(movimiento.getValor());
                    estadoCuentaDto.setSaldoDisponible(movimiento.getSaldo());
                    return estadoCuentaDto;
                })
                .collect(Collectors.toList());
    }

    private Double obtenerSaldoInicial(Movimiento movimiento) {
        return movimiento.getTipoMovimiento().equals(TipoMovimiento.DEPOSITO.name())
                ? movimiento.getSaldo() - movimiento.getValor()
                : movimiento.getSaldo() + movimiento.getValor();
    }

}
