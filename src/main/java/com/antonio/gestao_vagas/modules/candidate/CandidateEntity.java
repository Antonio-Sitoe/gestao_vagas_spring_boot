package com.antonio.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Data
public class CandidateEntity {
  private UUID id;
  private String name;

  @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "O campo deve conter apenas letras e números")
  private String username;

  @Email(message = "O campo deve conter um email válido")
  private String email;

  @Length(min = 8, max = 12, message = "A senha deve conter entre 8 e 12 caracteres")
  private String password;

  private String description;

  private String curriculum;
}
