package com.examen.persona.DTO;

import lombok.Data;

@Data
public class PersonaDTO {
    private Long id;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
