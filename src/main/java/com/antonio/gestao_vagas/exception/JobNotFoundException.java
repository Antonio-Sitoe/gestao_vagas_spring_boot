package com.antonio.gestao_vagas.exception;

public class JobNotFoundException extends RuntimeException {
  public JobNotFoundException(String message) {
    super(message);
  }

}
