package com.antonio.gestao_vagas.modules.candidate.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antonio.gestao_vagas.modules.candidate.CandidateEntity;
import com.antonio.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @PostMapping("")
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
    try {
      var candidateSaved = this.createCandidateUseCase.execute(candidate);
      return ResponseEntity.status(HttpStatus.CREATED).body(candidateSaved);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

}
