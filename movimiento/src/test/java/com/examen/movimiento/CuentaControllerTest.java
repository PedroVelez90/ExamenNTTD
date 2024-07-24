package com.examen.movimiento;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.examen.movimiento.controller.CuentaController;
import com.examen.movimiento.dto.ClienteDto;
import com.examen.movimiento.dto.CuentaDto;
import com.examen.movimiento.dto.CuentaRequestDto;
import com.examen.movimiento.service.CuentaService;

@RunWith(MockitoJUnitRunner.class)
public class CuentaControllerTest {

    @Mock
    private CuentaService cuentaServ;

    @InjectMocks
    private CuentaController cuentaController;

    @Test
    public void testCrearCuenta() {

        CuentaRequestDto cuentaDto = new CuentaRequestDto();
        cuentaDto.setIdentificacion("0987654321");
        cuentaDto.setNumeroCuenta(12345l);
        cuentaDto.setSaldoInicial(100.0);
        cuentaDto.setTipoCuenta("Ahorros");
        cuentaDto.setEstado(true);

        CuentaDto cuentaNueva = new CuentaDto();
        ClienteDto cliente = new ClienteDto();
        cliente.setId(1l);
        cuentaNueva.setCliente(cliente);
        cuentaNueva.setNumeroCuenta(12345l);
        cuentaNueva.setSaldoInicial(100.0);
        cuentaNueva.setTipoCuenta("Ahorros");
        cuentaNueva.setEstado(true);

        when(cuentaServ.guardar(cuentaDto)).thenReturn(cuentaNueva);

        ResponseEntity<CuentaDto> response = cuentaController.crearCuenta(cuentaDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cuentaNueva, response.getBody());
    }
}
