package com.examen.persona.enums;

import lombok.Getter;

@Getter
public enum ResponseDictionary {
    OK(
            "0",
            200,
            "Transaccion exitosa"),
    CREATE(
            "0",
            201,
            "Creacion exitosa"),
    INTERNAL_ERROR(
            "9999",
            500,
            "Ocurrio un error interno en el servidor"),

    BAD_REQUEST(
            "4001",
            400,
            "Peticion invalida. Favor verificar parametros"),
    REGISTRO_NO_EXISTE(
            "4003",
            400,
            "No existen regitros para la consulta"),
    REGISTRO_EXISTENTE(
            "4002",
            400,
            "Existen registro ingresados con estos datos"),;

    private final String code;
    private final int httpStatus;
    private final String message;

    ResponseDictionary(String code, int httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
