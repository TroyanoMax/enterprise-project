package com.crg.enterprisews.service;

import com.crg.enterprisews.domain.Empresa;
import com.crg.enterprisews.dto.EmpresaDTO;
import com.crg.enterprisews.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<Empresa> obtenerEmpresasAdheridasUltimoMes() {
        LocalDate localDate = LocalDate.now().minusMonths(1);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return empresaRepository.findByFechaAdhesionAfterAndIsActive(date, true);
    }

    @Override
    public void adherirEmpresa(EmpresaDTO empresa) {

        Empresa em = modelMapper.map(empresa, Empresa.class);
        empresaRepository.save(em);
    }
}