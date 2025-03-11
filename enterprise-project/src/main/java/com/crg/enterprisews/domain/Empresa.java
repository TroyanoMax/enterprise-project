package com.crg.enterprisews.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@Table(name = "EMPRESAS", schema = "ENTPS_DATA")
@NoArgsConstructor
@AllArgsConstructor
public class Empresa extends AuditEntity {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "CUIT")
    private String cuit;

    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;

    @Column(name = "FECHA_ADHESION")
    private LocalDate fechaAdhesion;
}
