package com.examen.movimiento.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.List;

import com.examen.movimiento.exception.FinancieroException;
import com.examen.movimiento.repository.CuentaRepository;
import com.examen.movimiento.service.CuentaService;
import com.examen.movimiento.validator.CuentaValidator;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.examen.movimiento.dto.ClienteDto;
import com.examen.movimiento.dto.CuentaDto;
import com.examen.movimiento.dto.CuentaRequestDto;
import com.examen.movimiento.dto.MovimientoRequestDto;
import com.examen.movimiento.dto.RespuestaDTO;
import com.examen.movimiento.entity.Cuenta;
import com.examen.movimiento.enums.TipoMovimiento;
import com.examen.movimiento.service.ClienteService;
import com.examen.movimiento.service.MovimientoService;

import lombok.AllArgsConstructor;
@Slf4j
@Service
@AllArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    private final ModelMapper modelMapper;

    private final CuentaValidator cuentaValid;

    private final ClienteService clienteServ;

    private final CuentaRepository cuentaRepo;

    private final MovimientoService movimientoServ;

    @Override
    public CuentaDto guardar(CuentaRequestDto cuentaReq) {
        cuentaValid.validarNulosVacios(cuentaReq);
        Cuenta cuentaEntity = modelMapper.map(cuentaReq, Cuenta.class);
        ClienteDto clienteDto = clienteServ.consultarCliente(cuentaReq.getIdentificacion());
        log.info("cliente dto: {}",clienteDto);
        if (clienteDto != null) {
            cuentaEntity.setClienteId(clienteDto.getResultado().getId());
        } else {
            throw new FinancieroException("El Cliente no existe");
        }

        Cuenta cuentaNueva = cuentaRepo.save(cuentaEntity);
        MovimientoRequestDto movimientoDto = new MovimientoRequestDto();
        movimientoDto.setFecha(new Date());
        movimientoDto.setTipoMovimiento(TipoMovimiento.DEPOSITO.name());
        movimientoDto.setValor(cuentaNueva.getSaldoInicial());
        movimientoDto.setSaldo(cuentaNueva.getSaldoInicial());
        movimientoDto.setNumeroCuenta(cuentaNueva.getNumeroCuenta());
        movimientoDto.setTipoCuenta(cuentaNueva.getTipoCuenta());
        movimientoServ.guardar(movimientoDto);
        CuentaDto cuentaDto = modelMapper.map(cuentaNueva, CuentaDto.class);
        cuentaDto.setCliente(clienteDto);
        return cuentaDto;
    }

    @Override
    public CuentaDto actualizar(CuentaRequestDto cuentaReq) {
        cuentaValid.validarNulosVacios(cuentaReq);
        Cuenta cuentaEntity = modelMapper.map(cuentaReq, Cuenta.class);
        Cuenta cuentaNueva = cuentaRepo.save(cuentaEntity);
        return modelMapper.map(cuentaNueva, CuentaDto.class);
    }

    @Override
    public RespuestaDTO eliminar(Long numeroCuenta) {
        RespuestaDTO resultado = new RespuestaDTO();
        Optional<Cuenta> cuenta = cuentaRepo.findByNumeroCuenta(numeroCuenta);
        if (cuenta.isPresent()) {
            Cuenta cuentaEntidad = cuenta.get();

            if (cuentaEntidad.isEstado()) {
                cuentaEntidad.setEstado(false);
                cuentaRepo.save(cuentaEntidad);
                resultado.setMensaje("Cuenta eliminada correctamente");
            } else {
                throw new FinancieroException(
                        "La cuenta con número " + numeroCuenta + " ya se encuentra eliminada");
            }
        } else {
            throw new FinancieroException("No se encontro cuenta con el número " + numeroCuenta);
        }
        return resultado;
    }

    @Override
    public List<CuentaDto> consultar() {
        List<Cuenta> cuentas = cuentaRepo.findAll();
        return cuentas.stream()
                .map(cuenta -> modelMapper.map(cuenta, CuentaDto.class))
                .collect(Collectors.toList());
    }
}
