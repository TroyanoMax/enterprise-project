package com.crg.enterprisews.controller;

import com.crg.enterprisews.domain.Transferencia;
import com.crg.enterprisews.dto.EmpresaDTO;
import com.crg.enterprisews.dto.EmpresaResponse;
import com.crg.enterprisews.dto.TransferenciaResponse;
import com.crg.enterprisews.service.TransferenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
@RequiredArgsConstructor
@Log4j2
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    /**
     * Listado transferencias realizadas el último mes.
     * @return body listado transferencias realizadas el último mes.
     */
    @Operation(summary = "Listado transferencians realziadas el último mes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado transferencians realziadas el último mes.",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaDTO.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Bad credentials",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RuntimeException.class)
                    ))
    })
    @GetMapping("/ultimo-mes")
    public ResponseEntity<TransferenciaResponse> obtenerTransferenciasUltimoMes() {
        log.info("Obteniendo lista de transferencias en el último mes");

        var response = transferenciaService.obtenerTransferenciasUltimoMes();
        if (response != null && !response.getItems().isEmpty()) {
            log.info("Transferencias Realziadas: {}", response.getItems());
            return ResponseEntity.ok(response);
        } else {
            log.info("No se encontraron transferencias realizadas en el último mes");
            return ResponseEntity.ok(response);
        }
    }

}