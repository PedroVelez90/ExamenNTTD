package com.examen.persona.service.impl;

import com.examen.persona.DTO.ClienteDTO;
import com.examen.persona.DTO.RespuestaDTO;
import com.examen.persona.entity.Cliente;
import com.examen.persona.repository.ClienteRepository;
import com.examen.persona.service.ClienteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    private final ModelMapper modelMapper;

    @Override
    public ClienteDTO guardar(ClienteDTO clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO,Cliente.class);
        Cliente  nuevoCliente = clienteRepository.save(cliente);
        return modelMapper.map(nuevoCliente,ClienteDTO.class);

    }

    @Override
    public List<ClienteDTO> consultar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public ClienteDTO consultaClientePorIdentificacion(String identificacion){
        log.info("consultaClientePorIdentificacion: {}",identificacion);
        Optional<Cliente> cliente= clienteRepository.findByIdentificacion(identificacion);
        if(cliente.isPresent()){
            return modelMapper.map(cliente.get(),ClienteDTO.class);
        }else{
            throw new IllegalArgumentException();
        }
    }
    @Override
    public RespuestaDTO eliminar(String identificacion) {
        RespuestaDTO resultado = new RespuestaDTO();
        Optional<Cliente> cliente = clienteRepository.findByIdentificacion(identificacion);
        if (cliente.isPresent()) {
            Cliente clienteEntidad = cliente.get();
            if (clienteEntidad.isEstado()) {
                clienteEntidad.setEstado(false);
                clienteRepository.save(clienteEntidad);
            }
            resultado.setMensaje("Cliente eliminado correctamente");
        } else {
            throw new IllegalArgumentException();
        }
        return resultado;
    }

}
