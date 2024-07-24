package com.examen.movimiento.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examen.movimiento.dto.MovimientoDto;
import com.examen.movimiento.dto.EstadoCuentaDto;
import com.examen.movimiento.dto.MovimientoRequestDto;
import com.examen.movimiento.service.MovimientoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoServ;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<MovimientoDto> crearMovimiento(@RequestBody MovimientoRequestDto movimientoReq) {
        return new ResponseEntity<>(movimientoServ.guardar(movimientoReq), HttpStatus.OK);
    }

    @GetMapping(produces = "application/json", path = "/reportes")
    public ResponseEntity<List<EstadoCuentaDto>> consultarMovimientos(
            @RequestParam(required = true) String identificacion,
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date fechaInicio,
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date fechaFin) {
        return new ResponseEntity<>(movimientoServ.consultarEstadoCuenta(identificacion, fechaInicio, fechaFin),
                HttpStatus.OK);
    }
}
