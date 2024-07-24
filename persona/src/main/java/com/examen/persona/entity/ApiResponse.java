package com.examen.persona.entity;

import com.examen.persona.enums.ResponseDictionary;
import lombok.Data;

import java.util.List;

@Data
public class ApiResponse<T> {
    private String codigo;
    private String mensaje;
    private T resultado;

    public ApiResponse() {
    }

    public ApiResponse(String codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public static <T> ApiResponse<T> ok(T resultado) {
        ApiResponse<T> res = ok();
        res.setResultado(resultado);
        return res;
    }

    public static <T> ApiResponse<T> create(T resultado) {
        ApiResponse<T> res = create();
        res.setResultado(resultado);
        return res;
    }
    public static <T> ApiResponse<List<T>> ok(List<T> resultado) {
        ApiResponse<List<T>> res = ok();
        res.setResultado(resultado);
        return res;
    }

    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<>(ResponseDictionary.OK.getCode(), ResponseDictionary.OK.getMessage());
    }
    public static <T> ApiResponse<T> create() {
        return new ApiResponse<>(ResponseDictionary.CREATE.getCode(), ResponseDictionary.CREATE.getMessage());
    }

}
