package com.crg.enterprisews.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Enterprise Transaction Manager",
                version = "v1.0.0",
                description = "Documentación - Servicios para sitema de Transacciones"
        ),
        tags = {
                @Tag(name = "Nombre:", description = "enterprisews")
        }
)

public class OpenApiConfig {
}
