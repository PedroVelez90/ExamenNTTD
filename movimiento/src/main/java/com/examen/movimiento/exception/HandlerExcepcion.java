package com.examen.movimiento.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExcepcion {
    @ExceptionHandler(FinancieroException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> controlarExcepcion(FinancieroException ex) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", ex.getMessage());
        return respuesta;
    }
}