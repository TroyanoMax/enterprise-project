package com.crg.enterprisews.dto;

import com.crg.enterprisews.domain.Empresa;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaResponse {

    /** * lista item empresas. */
    @JsonProperty("items")
    private List<Empresa> items;

    /** * lista item empresas. */
    @JsonProperty("message")
    private String message;

}