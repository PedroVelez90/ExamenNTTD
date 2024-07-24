package com.examen.movimiento.dto;

import lombok.Data;
import java.util.Date;

@Data
public class EstadoCuentaDto {
    private Date fecha;
    private String cliente;
    private Long numeroCuenta;
    private String tipo;
    private Double saldoInicial;
    private boolean estado;
    private Double movimiento;
    private Double saldoDisponible;
}
