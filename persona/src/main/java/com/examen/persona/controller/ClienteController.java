package com.examen.persona.controller;

import com.examen.persona.DTO.ClienteDTO;
import com.examen.persona.DTO.RespuestaDTO;
import com.examen.persona.entity.ApiResponse;
import com.examen.persona.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("clientes")
public class ClienteController {
    private final ClienteService clienteService;
    @PostMapping
    public ResponseEntity<ApiResponse<ClienteDTO>> craerCliente(
            @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO response = clienteService.guardar(clienteDTO);
        return ResponseEntity.ok(ApiResponse.create(response));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<ApiResponse<List<ClienteDTO>>> consultarClientes() {
        List<ClienteDTO> response = clienteService.consultar();
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @GetMapping(produces = "application/json",path = "/{identificacion}")
    public ResponseEntity<ApiResponse<ClienteDTO>> consultarClientesPorIdentificacion(@PathVariable(name = "identificacion")
                                                                                           String identificacion) {
        ClienteDTO response = clienteService.consultaClientePorIdentificacion(identificacion);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
    @PutMapping
    public ResponseEntity<ApiResponse<ClienteDTO>> actualizarCliente(
            @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO responseUpdate = clienteService.guardar(clienteDTO);
        return ResponseEntity.ok(ApiResponse.ok(responseUpdate));
    }
    @DeleteMapping(produces = "application/json", path = "/{identificacion}")
    public ResponseEntity<ApiResponse<RespuestaDTO>> eliminarCliente(@PathVariable("identificacion")
                                                                         String identificacion) {
        RespuestaDTO response = clienteService.eliminar(identificacion);
        return ResponseEntity.ok(ApiResponse.ok(response));

    }


}
