package com.antonio.gestao_vagas.modules.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthCandidateResponseDTO {
  private String access_token;
  private Long expire_in;
}
