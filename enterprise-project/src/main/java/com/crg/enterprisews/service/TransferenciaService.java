package com.crg.enterprisews.service;

import com.crg.enterprisews.domain.Transferencia;
import com.crg.enterprisews.dto.TransferenciaResponse;

import java.util.List;

public interface TransferenciaService {
    TransferenciaResponse obtenerTransferenciasUltimoMes();
}