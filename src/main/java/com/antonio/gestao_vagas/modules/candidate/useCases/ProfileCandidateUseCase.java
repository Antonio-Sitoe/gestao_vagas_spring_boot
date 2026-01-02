package com.antonio.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.antonio.gestao_vagas.modules.candidate.CandidateRepository;
import com.antonio.gestao_vagas.modules.candidate.dto.CandidateProfileResponseDTO;

@Service
public class ProfileCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public CandidateProfileResponseDTO execute(UUID idCandidate) {
    var candidate = this.candidateRepository.findById(idCandidate).orElseThrow(() -> {
      throw new UsernameNotFoundException("Candidate not found");
    });

    return CandidateProfileResponseDTO.builder()
        .id(candidate.getId())
        .name(candidate.getName())
        .username(candidate.getUsername())
        .description(candidate.getDescription())
        .email(candidate.getEmail())
        .build();
  }

}
