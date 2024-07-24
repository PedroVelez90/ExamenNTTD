package com.examen.movimiento.dto;

import lombok.Data;

@Data
public class CuentaRequestDto {
    private Long numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private boolean estado;
    private String identificacion;
}
