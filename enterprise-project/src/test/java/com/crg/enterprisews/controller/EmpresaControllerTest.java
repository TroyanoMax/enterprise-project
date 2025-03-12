package com.crg.enterprisews.controller;

import com.crg.enterprisews.domain.Empresa;
import com.crg.enterprisews.dto.EmpresaDTO;
import com.crg.enterprisews.dto.EmpresaResponse;
import com.crg.enterprisews.service.EmpresaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpresaControllerTest {

    @Mock
    private EmpresaService empresaService;

    @InjectMocks
    private EmpresaController empresaController;

    @Test
    void testObtenerEmpresasAdheridasUltimoMesCuandoHayEmpresasDebeRetornarLista() {

        // Arrange
        Empresa empresa = Empresa.builder()
                .id(1L)
                .cuit("30-11111111-1")
                .razonSocial("Empresa A")
                .fechaAdhesion(new Date())
                .build();

        EmpresaResponse mockResponse = new EmpresaResponse(List.of(empresa), "Empresas encontradas");

        when(empresaService.obtenerEmpresasAdheridasUltimoMes()).thenReturn(mockResponse);

        // Act
        ResponseEntity<EmpresaResponse> response = empresaController.obtenerEmpresasAdheridasUltimoMes();

        // Assert
        assertNotNull(response);
        assertEquals(200,
                response.getStatusCode().value(),
                "El código de estado debería ser 200"
        );
        assertFalse(
                Objects.requireNonNull(response.getBody()).getItems().isEmpty(),
                "La lista de empresas no debería estar vacía"
        );
        verify(empresaService, times(1)).obtenerEmpresasAdheridasUltimoMes();
    }

    @Test
    void testObtenerEmpresasAdheridasUltimoMesCuandoNoHayEmpresasDebeRetornarListaVacia() {
        // Arrange
        EmpresaResponse mockResponse = new EmpresaResponse(Collections.emptyList(), "Sin empresas");

        when(empresaService.obtenerEmpresasAdheridasUltimoMes()).thenReturn(mockResponse);

        // Act
        ResponseEntity<EmpresaResponse> response = empresaController.obtenerEmpresasAdheridasUltimoMes();

        // Assert
        assertNotNull(response);
        assertEquals(200,
                response.getStatusCode().value(),
                "El código de estado debería ser 200"
        );
        assertTrue(
                Objects.requireNonNull(response.getBody())
                        .getItems().isEmpty(),
                "La lista de empresas debería estar vacía"
        );
        verify(empresaService, times(1)).obtenerEmpresasAdheridasUltimoMes();
    }

    @Test
    void testAdherirEmpresaCuandoEsNuevaDebeRetornarCreated() {
        // Arrange
        var nuevaEmpresa = Empresa.builder()
                .id(1L)
                .cuit("30-11111111-1")
                .razonSocial("Empresa A")
                .fechaAdhesion(new Date())
                .build();
        var mockResponse = new EmpresaResponse(List.of(nuevaEmpresa), "Empresa adherida");

        var body = EmpresaDTO.builder()
                .id(1L)
                .cuit("30-11111111-1")
                .razonSocial("Empresa A")
                .fechaAdhesion(new Date())
                .build();

        when(empresaService.adherirEmpresa(body)).thenReturn(mockResponse);

        // Act
        ResponseEntity<EmpresaResponse> response = empresaController.adherirEmpresa(body);

        // Assert
        assertNotNull(response);
        assertEquals(
                HttpStatus.CREATED.value(),
                response.getStatusCode().value(),
                "El código de estado debería ser 201"
        );
        assertFalse(
                Objects.requireNonNull(
                        response.getBody()).getItems().isEmpty(),
                "La empresa debería estar en la lista"
        );
        verify(empresaService, times(1)).adherirEmpresa(body);
    }

    @Test
    void testAdherirEmpresaCuandoYaExisteDebeRetornarConflict() {
        // Arrange
        var body = EmpresaDTO.builder()
                .id(1L)
                .cuit("30-11111111-1")
                .razonSocial("Empresa A")
                .fechaAdhesion(new Date())
                .build();

        when(empresaService.adherirEmpresa(body)).thenThrow(new IllegalArgumentException("Empresa existente"));

        // Act
        ResponseEntity<EmpresaResponse> response = empresaController.adherirEmpresa(body);

        // Assert
        assertNotNull(response);
        assertEquals(
                HttpStatus.CONFLICT.value(),
                response.getStatusCode().value(),
                "El código de estado debería ser 409"
        );
        assertEquals("Empresa Existente",
                Objects.requireNonNull(
                        response.getBody()).getMessage(),
                "El mensaje debería indicar que la empresa ya existe"
        );
        verify(empresaService, times(1)).adherirEmpresa(body);
    }

    @Test
    void testAdherirEmpresaCuandoFallaDebeRetornarInternalServerError() {
        // Arrange
        var empresaFallida = EmpresaDTO.builder()
                .id(1L)
                .cuit("30-11111111-1")
                .razonSocial("Empresa A")
                .fechaAdhesion(new Date())
                .build();

        when(empresaService.adherirEmpresa(empresaFallida)).thenThrow(new RuntimeException("Error inesperado"));

        // Act
        ResponseEntity<EmpresaResponse> response = empresaController.adherirEmpresa(empresaFallida);

        // Assert
        assertNotNull(response);
        assertEquals(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                response.getStatusCode().value(),
                "El código de estado debería ser 500"
        );
        assertTrue(
                Objects.requireNonNull(
                        response.getBody()).getMessage().contains(
                                "Error inesperado"),
                "El mensaje debería indicar un error inesperado"
        );
        verify(empresaService, times(1)).adherirEmpresa(empresaFallida);
    }
}
