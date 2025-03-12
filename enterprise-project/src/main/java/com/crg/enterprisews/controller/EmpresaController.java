package com.crg.enterprisews.controller;

import com.crg.enterprisews.domain.Empresa;
import com.crg.enterprisews.dto.EmpresaDTO;
import com.crg.enterprisews.dto.EmpresaResponse;
import com.crg.enterprisews.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Collections;

@RestController
@RequestMapping("/empresas")
@RequiredArgsConstructor
@Log4j2
public class EmpresaController {

    private final EmpresaService empresaService;

    /**
     * Listado empresas adheridad el último mes.
     * @return body respuesta
     */
    @Operation(summary = "Listado empresas adheridad el último mes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado empresas adheridad el último mes.",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaDTO.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Bad credentials",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RuntimeException.class)
                    ))}
    )
    @GetMapping(path = "/adheridas-ultimo-mes",
    produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EmpresaResponse> obtenerEmpresasAdheridasUltimoMes() {
        log.info("Obteniendo empresas adheridas en el último mes");

        var response = empresaService.obtenerEmpresasAdheridasUltimoMes();

        if (response != null && !response.getItems().isEmpty()) {
            log.info("Empresas obtenidas: {}", response.getItems());
            return ResponseEntity.ok(response);
        } else {
            log.info("No se encontraron empresas adheridas en el último mes");
            return ResponseEntity.ok(response);
        }
    }

    /**
     * Listado empresas adheridad el último mes.
     * @return body respuesta
     */
    @Operation(summary = "Listado empresas adheridad el último mes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado empresas adheridad el último mes.",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaDTO.class)
                    )}),
            @ApiResponse(responseCode = "403", description = "Bad credentials",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RuntimeException.class)
                    )),
            @ApiResponse(responseCode = "409", description = "La empresa ya existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = IllegalArgumentException.class)
                    )),
            @ApiResponse(responseCode = "500", description = "Error inesperado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = HttpServerErrorException.InternalServerError.class)
                    ))
    })
    @PostMapping("/adherir")
    public ResponseEntity<EmpresaResponse> adherirEmpresa(
            @Valid @RequestBody EmpresaDTO empresa) {
        log.info("Solicitud para adherir empresa con CUIT: {}", empresa.getCuit());

        try {
            EmpresaResponse response = empresaService.adherirEmpresa(empresa);
            log.info("Empresa adherida exitosamente: {}", response.getItems());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            log.warn("Error al adherir empresa: {}", e.getMessage());
            return new ResponseEntity<>(
                        new EmpresaResponse(null, "Empresa Existente"),
                        HttpStatus.CONFLICT
                    );
        } catch (Exception e) {
            log.error("Error inesperado al adherir empresa.", e);
            return new ResponseEntity<>(
                    new EmpresaResponse(null, "Error inesperado al adherir empresa."),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
