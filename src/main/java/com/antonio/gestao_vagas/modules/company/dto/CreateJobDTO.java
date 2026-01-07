package com.antonio.gestao_vagas.modules.company.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class CreateJobDTO {
  @Schema(description = "Vaga para pessoa desenvolvedora", example = "Frontend Developer")
  private String description;
  @Schema(description = "Beneficios da vaga", example = "Plano de saude, VR, VA, etc.")
  private String benefits;
  @Schema(description = "Nivel da vaga", example = "Junior, Pleno, Senior")
  private String level;
}
