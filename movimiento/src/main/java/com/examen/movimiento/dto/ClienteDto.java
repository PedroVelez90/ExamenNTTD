package com.examen.movimiento.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ClienteDto {
    private String codigo;
    private String mensaje;
    private Resultado resultado;

    @Data
    public static class Resultado{
        private String clave;
        private boolean estado;
        private Long id;
        private String nombre;
        private String genero;
        private Integer edad;
        private String identificacion;
        private String direccion;
        private String telefono;
    }
}

