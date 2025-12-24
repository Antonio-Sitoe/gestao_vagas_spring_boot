package com.antonio.gestao_vagas.modules.company.useCases;

import org.springframework.stereotype.Service;
import com.antonio.gestao_vagas.exception.UserFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.antonio.gestao_vagas.modules.company.entities.CompanyEntity;
import com.antonio.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
  @Autowired
  private CompanyRepository companyRepository;

  public CompanyEntity execute(CompanyEntity company) {
    this.companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail())
        .ifPresent(user -> {
          throw new UserFoundException("Company already exists");
        });
    return this.companyRepository.save(company);
  }

}
