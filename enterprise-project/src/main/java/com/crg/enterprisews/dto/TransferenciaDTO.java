package com.crg.enterprisews.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferenciaDTO extends AuditDTO {

    /** * Property. */
    @JsonProperty("id")
    private Long id;

    /** * Property. */
    @JsonProperty("importe")
    private Double importe;

    /** * Property. */
    @JsonProperty("cuentaDebito")
    private String cuentaDebito;

    /** * Property. */
    @JsonProperty("cuentaCredito")
    private String cuentaCredito;

    /** * Property. */
    @JsonProperty("empresaCuit")
    private String empresaCuit;

}