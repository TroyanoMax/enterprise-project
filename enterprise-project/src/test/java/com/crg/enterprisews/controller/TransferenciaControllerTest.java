package com.crg.enterprisews.controller;

import com.crg.enterprisews.domain.Transferencia;
import com.crg.enterprisews.dto.TransferenciaDTO;
import com.crg.enterprisews.dto.TransferenciaResponse;
import com.crg.enterprisews.service.TransferenciaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferenciaControllerTest {

    @Mock
    private TransferenciaService transferenciaService;

    @InjectMocks
    private TransferenciaController transferenciaController;

    @Test
    void obtenerTransferenciasUltimoMesCuandoHayTransferenciasDebeRetornarLista() {
        // Arrange
        TransferenciaResponse mockResponse = new TransferenciaResponse();
        mockResponse.setItems(List.of(
                Transferencia.builder()
                        .id(1L)
                        .importe(1000.0)
                        .cuentaDebito("12345")
                        .cuentaCredito("67890")
                        .empresaCuit("30-11111111-1")
                        .build()
        ));

        when(transferenciaService.obtenerTransferenciasUltimoMes()).thenReturn(mockResponse);

        // Act
        ResponseEntity<TransferenciaResponse> response = transferenciaController.obtenerTransferenciasUltimoMes();

        // Assert
        assertNotNull(response, "message");
        assertEquals(200, response.getStatusCode().value(), "message");
        assertFalse(Objects.requireNonNull(response.getBody()).getItems().isEmpty(), "message");
        verify(transferenciaService, times(1)).obtenerTransferenciasUltimoMes();
    }

    @Test
    void obtenerTransferenciasUltimoMesCuandoNoHayTransferenciasDebeRetornarListaVacia() {
        // Arrange
        TransferenciaResponse mockResponse = new TransferenciaResponse();
        mockResponse.setItems(Collections.emptyList());

        when(transferenciaService.obtenerTransferenciasUltimoMes()).thenReturn(mockResponse);

        // Act
        ResponseEntity<TransferenciaResponse> response = transferenciaController.obtenerTransferenciasUltimoMes();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(Objects.requireNonNull(response.getBody()).getItems().isEmpty());
        verify(transferenciaService, times(1)).obtenerTransferenciasUltimoMes();
    }

}
