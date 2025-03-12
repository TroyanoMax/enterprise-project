package com.crg.enterprisews.service;

import com.crg.enterprisews.dto.TransferenciaResponse;
import com.crg.enterprisews.repository.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransferenciaServiceImpl implements TransferenciaService {

    /** Repository. */
    private final TransferenciaRepository transferenciaRepository;

    /**
     * Devuelve las transferencias del último mes.
     * @return - listado de transferencias último mes.
     */
    @Override
    public TransferenciaResponse obtenerTransferenciasUltimoMes() {

        LocalDate localDate = LocalDate.now().minusMonths(1);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return Optional.ofNullable(
                        transferenciaRepository.findByCreatedDateAfterAndIsActive(date, true)
                )
                .map(transferencia -> {
                    var empresaResponse = new TransferenciaResponse();
                    empresaResponse.setItems(transferencia);
                    return empresaResponse;
                })
                .orElseGet(() -> {
                    var transferenciaResponse = new TransferenciaResponse();
                    transferenciaResponse.setItems(Collections.emptyList());
                    return transferenciaResponse;
                });
    }
}