package com.crg.enterprisews.service;

import com.crg.enterprisews.domain.Empresa;
import com.crg.enterprisews.dto.EmpresaDTO;

import java.util.List;

public interface EmpresaService {
    List<Empresa> obtenerEmpresasAdheridasUltimoMes();
    void adherirEmpresa(EmpresaDTO empresa);
}
