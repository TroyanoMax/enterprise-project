package com.crg.enterprisews.service;

import com.crg.enterprisews.domain.Empresa;
import com.crg.enterprisews.dto.EmpresaDTO;
import com.crg.enterprisews.dto.EmpresaResponse;
import com.crg.enterprisews.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    /**
     * EmpresaRepository.
     */
    private final EmpresaRepository empresaRepository;

    /**
     * ModelMapper.
     */
    private final ModelMapper modelMapper;

    /**
     * Listado de empresas adheridas el último mes.
     * @return - Listado de empresas adheridas el último mes.
     */
    @Override
    public EmpresaResponse obtenerEmpresasAdheridasUltimoMes() {
        LocalDate localDate = LocalDate.now().minusMonths(1);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return Optional.ofNullable(
                empresaRepository.findByFechaAdhesionAfterAndIsActive(date, true)
                )
                .map(empresas -> {
                    var empresaResponse = new EmpresaResponse();
                    empresaResponse.setItems(empresas);
                    return empresaResponse;
                })
                .orElseGet(() -> {
                    var empresaResponse = new EmpresaResponse();
                    empresaResponse.setItems(Collections.emptyList());
                    return empresaResponse;
                });
    }

    @Override
    public EmpresaResponse adherirEmpresa(EmpresaDTO empresaDTO) {
        Empresa empresa = modelMapper.map(empresaDTO, Empresa.class);

        var existingEmpresa = empresaRepository.findByCuitAndIsActive(empresa.getCuit(), true);
        if (!existingEmpresa.isEmpty()) {
            throw new IllegalArgumentException("La empresa con CUIT " + empresa.getCuit() + " ya existe.");
        }

        Empresa savedEmpresa = empresaRepository.save(empresa);

        EmpresaResponse response = new EmpresaResponse();
        response.setItems(Collections.singletonList(savedEmpresa));

        return response;
    }
}