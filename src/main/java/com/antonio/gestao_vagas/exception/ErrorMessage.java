package com.antonio.gestao_vagas.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
  private String message;
  private String field;
}
