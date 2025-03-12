package com.crg.enterprisews.dto;

import com.crg.enterprisews.domain.Transferencia;
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
public class TransferenciaResponse {

    /** * lista item transferencias. */
    @JsonProperty("items")
    private List<Transferencia> items;

    /** * messages. */
    @JsonProperty("message")
    private String message;

}