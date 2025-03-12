package com.crg.enterprisews.service;

import com.crg.enterprisews.domain.Transferencia;
import com.crg.enterprisews.dto.TransferenciaResponse;
import com.crg.enterprisews.repository.TransferenciaRepository;
import com.crg.enterprisews.service.TransferenciaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.engine.execution.JupiterEngineExecutionContext;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferenciaServiceImplTest {

    @Mock
    private TransferenciaRepository transferenciaRepository;

    @InjectMocks
    private TransferenciaServiceImpl transferenciaService;

    private Transferencia transferencia;

    @BeforeEach
    void setUp() {
        transferencia = new Transferencia();
        transferencia.setId(1L);
        transferencia.setImporte(5000.00);
        transferencia.setCreatedDate(Date.from(LocalDate.now().minusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        transferencia.setActive(true);
    }

    @Test
    void testObtenerTransferenciasUltimoMesCuandoHayTransferenciasDebeRetornarLista() {
        // Arrange
        when(transferenciaRepository.findByCreatedDateAfterAndIsActive(any(Date.class), eq(true)))
                .thenReturn(List.of(transferencia));

        // Act
        TransferenciaResponse response = transferenciaService.obtenerTransferenciasUltimoMes();

        // Assert
        assertNotNull(response);
        assertFalse(response.getItems().isEmpty(), "La lista de transferencias no debería estar vacía");
        assertEquals(1, response.getItems().size());
        verify(transferenciaRepository, times(1)).findByCreatedDateAfterAndIsActive(any(Date.class), eq(true));
    }

    @Test
    void testObtenerTransferenciasUltimoMesCuandoNoHayTransferenciasDebeRetornarListaVacia() {
        // Arrange
        when(transferenciaRepository.findByCreatedDateAfterAndIsActive(any(Date.class), eq(true)))
                .thenReturn(null);

        // Act
        TransferenciaResponse response = transferenciaService.obtenerTransferenciasUltimoMes();

        // Assert
        assertNotNull(response);
        assertTrue(response.getItems().isEmpty(), "La lista de transferencias debería estar vacía");
        verify(transferenciaRepository, times(1)).findByCreatedDateAfterAndIsActive(any(Date.class), eq(true));
    }

}
