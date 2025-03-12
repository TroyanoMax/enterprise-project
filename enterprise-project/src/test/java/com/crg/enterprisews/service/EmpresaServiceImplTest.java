package com.crg.enterprisews.service;

import com.crg.enterprisews.domain.Empresa;
import com.crg.enterprisews.dto.EmpresaDTO;
import com.crg.enterprisews.dto.EmpresaResponse;
import com.crg.enterprisews.repository.EmpresaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpresaServiceImplTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EmpresaServiceImpl empresaService;

    private Empresa empresa;
    private EmpresaDTO empresaDTO;

    @BeforeEach
    void setUp() {
        empresa = new Empresa();
        empresa.setId(1L);
        empresa.setCuit("30-11111111-1");
        empresa.setRazonSocial("Empresa A");
        empresa.setFechaAdhesion(Date.from(LocalDate.now().minusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        empresa.setActive(true);

        empresaDTO = new EmpresaDTO();
        empresaDTO.setCuit("30-11111111-1");
        empresaDTO.setRazonSocial("Empresa A");
    }

    @Test
    void testObtenerEmpresasAdheridasUltimoMesCuandoHayEmpresasDebeRetornarLista() {
        // Arrange
        when(empresaRepository.findByFechaAdhesionAfterAndIsActive(any(Date.class), eq(true)))
                .thenReturn(List.of(empresa));

        // Act
        EmpresaResponse response = empresaService.obtenerEmpresasAdheridasUltimoMes();

        // Assert
        assertNotNull(response);
        assertFalse(response.getItems().isEmpty(), "La lista de empresas no debería estar vacía");
        verify(empresaRepository, times(1)).findByFechaAdhesionAfterAndIsActive(any(Date.class), eq(true));
    }

    @Test
    void testObtenerEmpresasAdheridasUltimoMesCuandoNoHayEmpresasDebeRetornarListaVacia() {
        // Arrange
        when(empresaRepository.findByFechaAdhesionAfterAndIsActive(any(Date.class), eq(true)))
                .thenReturn(null);

        // Act
        EmpresaResponse response = empresaService.obtenerEmpresasAdheridasUltimoMes();

        // Assert
        assertNotNull(response);
        assertTrue(response.getItems().isEmpty(), "La lista de empresas debería estar vacía");
        verify(empresaRepository, times(1)).findByFechaAdhesionAfterAndIsActive(any(Date.class), eq(true));
    }

    @Test
    void testAdherirEmpresaCuandoEsNuevaDebeGuardarlaYRetornar() {
        // Arrange
        when(empresaRepository.findByCuitAndIsActive(empresaDTO.getCuit(), true))
                .thenReturn(Collections.emptyList());

        when(modelMapper.map(empresaDTO, Empresa.class)).thenReturn(empresa);
        when(empresaRepository.save(empresa)).thenReturn(empresa);

        // Act
        EmpresaResponse response = empresaService.adherirEmpresa(empresaDTO);

        // Assert
        assertNotNull(response);
        assertFalse(response.getItems().isEmpty(), "La empresa debería estar en la lista");
        assertEquals(empresa.getCuit(), response.getItems().get(0).getCuit());
        verify(empresaRepository, times(1)).findByCuitAndIsActive(empresaDTO.getCuit(), true);
        verify(empresaRepository, times(1)).save(empresa);
    }

    @Test
    void testAdherirEmpresaCuandoYaExisteDebeLanzarExcepcion() {

        // Arrange
        when(modelMapper.map(empresaDTO, Empresa.class)).thenReturn(empresa);
        when(empresaRepository.findByCuitAndIsActive(empresaDTO.getCuit(), true))
                .thenReturn(List.of(empresa));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> empresaService.adherirEmpresa(empresaDTO));

        assertEquals("La empresa con CUIT " + empresaDTO.getCuit() + " ya existe.", exception.getMessage());
        verify(empresaRepository, times(1)).findByCuitAndIsActive(empresaDTO.getCuit(), true);
        verify(empresaRepository, never()).save(any());
    }
}
