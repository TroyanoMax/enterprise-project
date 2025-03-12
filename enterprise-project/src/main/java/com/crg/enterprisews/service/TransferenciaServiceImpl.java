package com.crg.enterprisews.service;

import com.crg.enterprisews.domain.Transferencia;
import com.crg.enterprisews.repository.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferenciaServiceImpl implements TransferenciaService {

    /** Repository. */
    private final TransferenciaRepository transferenciaRepository;

    @Override
    public List<Transferencia> obtenerTransferenciasUltimoMes() {
        LocalDate localDate = LocalDate.now().minusMonths(1);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return transferenciaRepository.findByCreatedDateAfterAndIsActive(date, true);
    }
}