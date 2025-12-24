package com.antonio.gestao_vagas.modules.company.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.antonio.gestao_vagas.modules.company.entities.CompanyEntity;
import com.antonio.gestao_vagas.modules.company.useCases.CreateCompanyUseCase;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/company")
public class CompanyController {

  @Autowired
  private CreateCompanyUseCase createCompanyUseCase;

  @PostMapping("")
  public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity company) {
    try {
      var companySaved = this.createCompanyUseCase.execute(company);
      return ResponseEntity.status(HttpStatus.CREATED).body(companySaved);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

}
