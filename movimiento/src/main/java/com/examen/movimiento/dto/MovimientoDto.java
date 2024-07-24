package com.examen.movimiento.dto;

import java.util.Date;

import com.examen.movimiento.entity.Cuenta;

import lombok.Data;

@Data
public class MovimientoDto {
    private Long id;
    private Date fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
    private Cuenta cuenta;
}
