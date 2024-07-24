package com.examen.movimiento.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examen.movimiento.dto.CuentaDto;
import com.examen.movimiento.dto.CuentaRequestDto;
import com.examen.movimiento.dto.RespuestaDTO;

@Service
public interface CuentaService {
    public CuentaDto guardar(CuentaRequestDto cuentaReq);

    public CuentaDto actualizar(CuentaRequestDto cuentaReq);

    public RespuestaDTO eliminar(Long numeroCuenta);
 
    public List<CuentaDto> consultar();
}
