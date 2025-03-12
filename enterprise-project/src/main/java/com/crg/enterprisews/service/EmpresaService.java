package com.crg.enterprisews.service;

import com.crg.enterprisews.dto.EmpresaDTO;
import com.crg.enterprisews.dto.EmpresaResponse;


public interface EmpresaService {
    EmpresaResponse obtenerEmpresasAdheridasUltimoMes();
    EmpresaResponse adherirEmpresa(EmpresaDTO empresa);
}
