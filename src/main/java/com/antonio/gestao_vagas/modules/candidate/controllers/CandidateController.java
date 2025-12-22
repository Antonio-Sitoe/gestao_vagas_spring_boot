package com.antonio.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antonio.gestao_vagas.modules.candidate.CandidateEntity;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @PostMapping("")
  public String create(@Valid @RequestBody CandidateEntity candidate) {
    System.out.println("EMAIL: " + candidate.getEmail());
    return "Candidate created successfully";
  }

}
