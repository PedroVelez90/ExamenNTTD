package com.examen.persona.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClienteDTO extends PersonaDTO{
    private String clave;
    private boolean estado;
}
