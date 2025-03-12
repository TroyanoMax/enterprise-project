package com.crg.enterprisews.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
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
