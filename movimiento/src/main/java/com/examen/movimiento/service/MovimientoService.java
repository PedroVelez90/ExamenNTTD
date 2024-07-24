package com.examen.movimiento.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.examen.movimiento.dto.MovimientoRequestDto;
import com.examen.movimiento.dto.MovimientoDto;
import com.examen.movimiento.dto.EstadoCuentaDto;

@Service
public interface MovimientoService {
    public MovimientoDto guardar(MovimientoRequestDto movimientoReq);

    public List<EstadoCuentaDto> consultarEstadoCuenta(String identificacion, Date fechaInicio, Date fechaFin);
}
