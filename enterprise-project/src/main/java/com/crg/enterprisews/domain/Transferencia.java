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

@Entity
@Getter
@Setter
@Builder
@Table(name = "TRANSFERENCIAS", schema = "ENTPS_DATA")
@NoArgsConstructor
@AllArgsConstructor
public class Transferencia extends AuditEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "importe")
    private Double importe;

    @Column(name = "cuentaDebito")
    private String cuentaDebito;

    @Column(name = "cuentaCredito")
    private String cuentaCredito;

    @Column(name = "empresaCuit")
    private String empresaCuit;
}