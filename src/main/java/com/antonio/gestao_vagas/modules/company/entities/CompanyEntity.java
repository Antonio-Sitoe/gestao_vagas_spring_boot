package com.antonio.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Data
@Entity(name = "company")
public class CompanyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "O campo deve conter apenas letras e números")
  private String username;

  @NotBlank(message = "O campo email é obrigatório")
  @Email(message = "O campo deve conter um email válido")
  private String email;

  @NotBlank(message = "O campo password é obrigatório")
  @Length(min = 8, message = "A senha deve conter no mínimo 8 caracteres")
  private String password;

  private String description;
  private String website;
  private String logo;
  private LocalDateTime createdAt;
}
