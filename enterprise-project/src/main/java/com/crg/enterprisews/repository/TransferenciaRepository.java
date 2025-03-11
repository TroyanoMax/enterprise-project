package com.crg.enterprisews.repository;

import com.crg.enterprisews.domain.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    List<Transferencia> findByFechaAfter(LocalDate fecha, Boolean isActive);
}
