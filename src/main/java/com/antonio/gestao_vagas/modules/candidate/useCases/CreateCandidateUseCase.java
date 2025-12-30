package com.antonio.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.antonio.gestao_vagas.exception.UserFoundException;
import com.antonio.gestao_vagas.modules.candidate.CandidateEntity;
import com.antonio.gestao_vagas.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private CandidateRepository candidateRepository;

  public CandidateEntity execute(CandidateEntity candidate) {
    this.candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
        .ifPresent(user -> {
          throw new UserFoundException("Candidate already exists");
        });
    var password = this.passwordEncoder.encode(candidate.getPassword());
    candidate.setPassword(password);
    return this.candidateRepository.save(candidate);

  }
}
