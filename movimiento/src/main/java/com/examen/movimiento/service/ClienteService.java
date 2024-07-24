package com.examen.movimiento.service;

import com.examen.movimiento.dto.ClienteDto;

public interface ClienteService {
    ClienteDto consultarCliente(String identificacion);
}
