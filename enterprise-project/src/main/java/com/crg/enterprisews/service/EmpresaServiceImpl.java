package com.crg.enterprisews.service;

import com.crg.enterprisews.domain.Empresa;
import com.crg.enterprisews.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {
    private final EmpresaRepository empresaRepository;

    @Override
    public List<Empresa> obtenerEmpresasAdheridasUltimoMes() {
        return empresaRepository.findByFechaAdhesionAfter(LocalDate.now().minusMonths(1), true);
    }

    @Override
    public void adherirEmpresa(Empresa empresa) {
        empresaRepository.save(empresa);
    }
}