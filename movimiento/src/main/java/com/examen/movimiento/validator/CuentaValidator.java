package com.examen.movimiento.validator;

import com.examen.movimiento.exception.FinancieroException;
import org.springframework.stereotype.Component;
import com.examen.movimiento.dto.CuentaRequestDto;

@Component
public class CuentaValidator {
    public void validarNulosVacios(CuentaRequestDto cuentaDto) {
        if (cuentaDto.getNumeroCuenta() == null || cuentaDto.getNumeroCuenta() <= 0) {
            throw new FinancieroException("Se requiere el número de Cuenta");
        }
        if (cuentaDto.getTipoCuenta() == null || cuentaDto.getTipoCuenta().equals("")) {
            throw new FinancieroException("Se requiere el tipo de cuenta");
        }
        if (!cuentaDto.getTipoCuenta().equals("Ahorros") && !cuentaDto.getTipoCuenta().equals("Corriente")) {
            throw new FinancieroException("El tipo de cuenta es incorrecto");
        }
        if (cuentaDto.getSaldoInicial() == null || cuentaDto.getSaldoInicial() <= 0) {
            throw new FinancieroException("Se requiere el saldo inicial");
        }
        if (cuentaDto.getIdentificacion() == null || cuentaDto.getIdentificacion().equals("")) {
            throw new FinancieroException("Se requiere la identificación del cliente");
        }

    }
}
