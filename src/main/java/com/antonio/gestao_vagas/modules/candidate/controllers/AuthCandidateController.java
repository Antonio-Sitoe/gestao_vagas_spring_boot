package com.antonio.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.antonio.gestao_vagas.modules.candidate.dto.AuthCandidateDTO;
import com.antonio.gestao_vagas.modules.candidate.useCases.AuthCandidateUseCase;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

  @Autowired
  private AuthCandidateUseCase authCandidateUseCase;

  @PostMapping("/auth")
  public ResponseEntity<Object> auth(@RequestBody AuthCandidateDTO authCandidateDTO) {
    try {
      var token = this.authCandidateUseCase.execute(authCandidateDTO);
      return ResponseEntity.status(HttpStatus.OK).body(Map.of("token", token));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
