package com.examen.persona.service.impl;

import com.examen.persona.DTO.ClienteDTO;
import com.examen.persona.DTO.RespuestaDTO;
import com.examen.persona.entity.Cliente;
import com.examen.persona.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {
    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;
    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setIdentificacion("12345");
        cliente.setEstado(true);

        clienteDTO = new ClienteDTO();
        clienteDTO.setIdentificacion("12345");
    }

    @Test
    void testGuardar() {
        when(modelMapper.map(clienteDTO, Cliente.class)).thenReturn(cliente);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        when(modelMapper.map(cliente, ClienteDTO.class)).thenReturn(clienteDTO);

        ClienteDTO result = clienteService.guardar(clienteDTO);

        assertNotNull(result);
        assertEquals(clienteDTO.getIdentificacion(), result.getIdentificacion());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testConsultar() {
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));
        when(modelMapper.map(cliente, ClienteDTO.class)).thenReturn(clienteDTO);

        List<ClienteDTO> result = clienteService.consultar();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(clienteDTO.getIdentificacion(), result.get(0).getIdentificacion());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testConsultaClientePorIdentificacion() {
        when(clienteRepository.findByIdentificacion("12345")).thenReturn(Optional.of(cliente));
        when(modelMapper.map(cliente, ClienteDTO.class)).thenReturn(clienteDTO);

        ClienteDTO result = clienteService.consultaClientePorIdentificacion("12345");

        assertNotNull(result);
        assertEquals(clienteDTO.getIdentificacion(), result.getIdentificacion());
        verify(clienteRepository, times(1)).findByIdentificacion("12345");
    }

    @Test
    void testConsultaClientePorIdentificacionNoEncontrado() {
        when(clienteRepository.findByIdentificacion("12345")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            clienteService.consultaClientePorIdentificacion("12345");
        });
        verify(clienteRepository, times(1)).findByIdentificacion("12345");
    }

    @Test
    void testEliminar() {
        when(clienteRepository.findByIdentificacion("12345")).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        RespuestaDTO result = clienteService.eliminar("12345");

        assertNotNull(result);
        assertEquals("Cliente eliminado correctamente", result.getMensaje());
        verify(clienteRepository, times(1)).findByIdentificacion("12345");
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testEliminarNoEncontrado() {
        when(clienteRepository.findByIdentificacion("12345")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            clienteService.eliminar("12345");
        });
        verify(clienteRepository, times(1)).findByIdentificacion("12345");
    }
}
