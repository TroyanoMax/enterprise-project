package com.crg.enterprisews.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@Table(
        name = "EMPRESAS",
        schema = "ENTPS_DATA",
        uniqueConstraints = @UniqueConstraint(columnNames = "cuit")
)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Empresa extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CUIT")
    private String cuit;

    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;

    @Column(name = "FECHA_ADHESION")
    private Date fechaAdhesion;
}
