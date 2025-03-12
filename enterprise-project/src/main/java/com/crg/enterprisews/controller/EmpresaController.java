package com.crg.enterprisews.controller;

import com.crg.enterprisews.domain.Empresa;
import com.crg.enterprisews.dto.EmpresaDTO;
import com.crg.enterprisews.service.EmpresaService;
//import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
//@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/adheridas-ultimo-mes")
    public List<Empresa> obtenerEmpresasAdheridasUltimoMes() {
        return empresaService.obtenerEmpresasAdheridasUltimoMes();
    }

    @PostMapping("/adherir")
    public void adherirEmpresa(@RequestBody EmpresaDTO empresa) {
        empresaService.adherirEmpresa(empresa);
    }
}
