package com.crg.enterprisews.service;

import com.crg.enterprisews.domain.Empresa;
import com.crg.enterprisews.dto.EmpresaDTO;
import com.crg.enterprisews.dto.EmpresaResponse;

import java.util.List;

public interface EmpresaService {
    EmpresaResponse obtenerEmpresasAdheridasUltimoMes();
    EmpresaResponse adherirEmpresa(EmpresaDTO empresa);
}
