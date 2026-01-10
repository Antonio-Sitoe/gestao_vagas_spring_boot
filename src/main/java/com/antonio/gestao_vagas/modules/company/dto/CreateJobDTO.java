package com.antonio.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobDTO {
  @Schema(description = "Vaga para pessoa desenvolvedora", example = "Frontend Developer")
  private String description;
  @Schema(description = "Beneficios da vaga", example = "Plano de saude, VR, VA, etc.")
  private String benefits;
  @Schema(description = "Nivel da vaga", example = "Junior, Pleno, Senior")
  private String level;
}
