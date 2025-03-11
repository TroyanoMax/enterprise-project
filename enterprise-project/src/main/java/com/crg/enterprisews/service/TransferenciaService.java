package com.crg.enterprisews.service;

import com.crg.enterprisews.domain.Transferencia;

import java.util.List;

public interface TransferenciaService {
    List<Transferencia> obtenerTransferenciasUltimoMes();
}