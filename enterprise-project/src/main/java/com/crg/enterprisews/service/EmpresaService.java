package com.crg.enterprisews.service;

import com.crg.enterprisews.domain.Empresa;

import java.util.List;

public interface EmpresaService {
    List<Empresa> obtenerEmpresasAdheridasUltimoMes();
    void adherirEmpresa(Empresa empresa);
}
