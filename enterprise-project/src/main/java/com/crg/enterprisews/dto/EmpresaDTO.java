package com.crg.enterprisews.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpresaDTO extends AuditDTO {

    /** * Property. */
    @JsonProperty("id")
    private Long id;

    /** * Property. */
    @JsonProperty("cuit")
    private String cuit;

    /** * Property. */
    @JsonProperty("razonSocial")
    private String razonSocial;

    /** * Property. */
    @JsonProperty("fechaAdhesion")
    private LocalDate fechaAdhesion;

}