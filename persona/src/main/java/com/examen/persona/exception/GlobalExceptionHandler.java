package com.examen.persona.exception;

import com.examen.persona.entity.ApiResponse;
import com.examen.persona.enums.ResponseDictionary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>> dataIntegrityViolationException(DataIntegrityViolationException e) {
        ApiResponse<Void> errorDetails = new ApiResponse<>();
        errorDetails.setCodigo(ResponseDictionary.REGISTRO_EXISTENTE.getCode());
        errorDetails.setMensaje(ResponseDictionary.REGISTRO_EXISTENTE.getMessage());
        log.error("Error DataIntegrityViolationException: {}", e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> illegalArgumentException(IllegalArgumentException e) {
        ApiResponse<Void> errorDetails = new ApiResponse<>();
        errorDetails.setCodigo(ResponseDictionary.REGISTRO_NO_EXISTE.getCode());
        errorDetails.setMensaje(ResponseDictionary.REGISTRO_NO_EXISTE.getMessage());
        log.error("Error IllegalArgumentException : {}", e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(UncategorizedSQLException e) {
        ApiResponse<Void> errorDetails = new ApiResponse<>();
        errorDetails.setCodigo(ResponseDictionary.INTERNAL_ERROR.getCode());
        errorDetails.setMensaje(ResponseDictionary.INTERNAL_ERROR.getMessage());
        log.error("Error exceptionHandler: {}", e.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
