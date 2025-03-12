package com.crg.enterprisews.repository;

import com.crg.enterprisews.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, String> {
    List<Empresa> findByFechaAdhesionAfterAndIsActive(Date fecha, Boolean isActive);
}
