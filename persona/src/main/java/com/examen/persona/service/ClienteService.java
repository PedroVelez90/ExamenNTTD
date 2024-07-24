package com.examen.persona.service;

import com.examen.persona.DTO.ClienteDTO;
import com.examen.persona.DTO.RespuestaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {
    public ClienteDTO guardar(ClienteDTO cliente);
    public List<ClienteDTO> consultar();
    public ClienteDTO consultaClientePorIdentificacion(String identificacion);
    public RespuestaDTO eliminar(String identificacion);
}
