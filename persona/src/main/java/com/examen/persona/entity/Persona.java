package com.examen.persona.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "personas")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String genero;

    private Integer edad;

    private String identificacion;

    private String direccion;

    private String telefono;
}
