package com.examen.movimiento.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.examen.movimiento.dto.ClienteDto;
import com.examen.movimiento.service.ClienteService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final RestTemplate restTemplate;

    @Override
    public ClienteDto consultarCliente(String identificacion) {
        return restTemplate.getForObject("http://localhost:8090/clientes/"+identificacion, ClienteDto.class);
    }

}
