package com.antonio.gestao_vagas.modules.company.useCases;

import org.springframework.stereotype.Service;
import com.antonio.gestao_vagas.exception.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.antonio.gestao_vagas.modules.company.entities.CompanyEntity;
import com.antonio.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public CompanyEntity execute(CompanyEntity company) {
    this.companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail())
        .ifPresent(user -> {
          throw new UserFoundException("Company already exists");
        });
    String password = this.passwordEncoder.encode(company.getPassword());
    company.setPassword(password);
    company.setCreatedAt(java.time.LocalDateTime.now());
    return this.companyRepository.save(company);
  }

}
