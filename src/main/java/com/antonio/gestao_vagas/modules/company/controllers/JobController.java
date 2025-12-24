package com.antonio.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antonio.gestao_vagas.modules.company.entities.JobEntity;
import com.antonio.gestao_vagas.modules.company.useCases.CreateJobUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping("")
  public ResponseEntity<Object> create(@Valid @RequestBody JobEntity job) {
    try {
      JobEntity jobSaved = this.createJobUseCase.execute(job);
      return ResponseEntity.status(HttpStatus.CREATED).body(jobSaved);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
