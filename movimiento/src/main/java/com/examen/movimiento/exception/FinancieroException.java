package com.examen.movimiento.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FinancieroException extends RuntimeException {

    public FinancieroException(String message) {
        super(message);
    }

}
