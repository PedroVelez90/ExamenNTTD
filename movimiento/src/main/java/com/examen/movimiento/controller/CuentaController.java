package com.examen.movimiento.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.movimiento.dto.CuentaDto;
import com.examen.movimiento.dto.CuentaRequestDto;
import com.examen.movimiento.dto.RespuestaDTO;
import com.examen.movimiento.service.CuentaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/cuentas")
public class CuentaController {
    
    private final CuentaService cuentaServ;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<CuentaDto> crearCuenta(@RequestBody CuentaRequestDto cuentaReq) {
        return new ResponseEntity<>(cuentaServ.guardar(cuentaReq), HttpStatus.OK);
    }

    @PutMapping(path = "/{numeroCuenta}", consumes = "application/json")
    public ResponseEntity<CuentaDto> actualizarCuenta(@PathVariable("numeroCuenta") Long numeroCuenta,
            @RequestBody CuentaRequestDto cuentaDto) {
        cuentaDto.setNumeroCuenta(numeroCuenta);
        return new ResponseEntity<>(cuentaServ.actualizar(cuentaDto), HttpStatus.OK);
    }

    @DeleteMapping(produces = "application/json", path = "/{numeroCuenta}")
    public ResponseEntity<RespuestaDTO> eliminarCuenta(@PathVariable("numeroCuenta") Long numeroCuenta) {
        return new ResponseEntity<>(cuentaServ.eliminar(numeroCuenta), HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CuentaDto>> consultarCuentas() {
        return new ResponseEntity<>(cuentaServ.consultar(), HttpStatus.OK);
    }
}
