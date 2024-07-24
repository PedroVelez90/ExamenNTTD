package com.examen.movimiento.dto;

import lombok.Data;

@Data
public class CuentaDto {
    private Long id;
    private Long numeroCuenta;
    private String tipoCuenta;
    private Double saldoInicial;
    private boolean estado;
    private ClienteDto cliente;
}
