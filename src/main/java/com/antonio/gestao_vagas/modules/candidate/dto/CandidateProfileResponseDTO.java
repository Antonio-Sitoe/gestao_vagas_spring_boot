package com.antonio.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateProfileResponseDTO {
  private UUID id;
  @Schema(description = "Nome do candidato", example = "John Doe")
  private String name;
  @Schema(description = "Username do candidato", example = "john_doe")
  private String username;
  @Schema(description = "Descrição do candidato", example = "Desenvolvedor Java")
  private String description;
  @Schema(description = "Email do candidato", example = "john.doe@example.com")
  private String email;
}
