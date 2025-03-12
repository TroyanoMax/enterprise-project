package com.crg.enterprisews.controller;

import com.crg.enterprisews.domain.Transferencia;
import com.crg.enterprisews.service.TransferenciaService;
//import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
//@RequiredArgsConstructor
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    @Autowired
    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping("/ultimo-mes")
    public List<Transferencia> obtenerTransferenciasUltimoMes() {
        return transferenciaService.obtenerTransferenciasUltimoMes();
    }

}