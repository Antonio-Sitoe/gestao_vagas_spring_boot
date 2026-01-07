package com.antonio.gestao_vagas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(new Info().title("Gestão de Vagas").description("API Responsavel pela gestao de vagas").version("1"))
        .schemaRequirement("bearerAuth", creaSecurityScheme());
  }

  private SecurityScheme creaSecurityScheme() {
    return new SecurityScheme().name("bearerAuth").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT");
  }
}
