package com.examen.movimiento.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MovimientoRequestDto {
    private Date fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
    private Long numeroCuenta;
    private String tipoCuenta;
}
