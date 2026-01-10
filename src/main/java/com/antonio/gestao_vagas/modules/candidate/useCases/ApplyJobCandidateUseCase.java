package com.antonio.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.antonio.gestao_vagas.exception.JobNotFoundException;
import com.antonio.gestao_vagas.exception.UserNotFoundException;
import com.antonio.gestao_vagas.modules.candidate.CandidateRepository;
import com.antonio.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import com.antonio.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import com.antonio.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private ApplyJobRepository applyJobRepository;

  @Autowired
  private JobRepository jobRepository;

  public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {
    this.candidateRepository.findById(idCandidate).orElseThrow(() -> {
      throw new UserNotFoundException("Utilizador nao encontrado");
    });

    this.jobRepository.findById(idJob).orElseThrow(() -> {
      throw new JobNotFoundException("Vaga nao encontrada");
    });

    ApplyJobEntity Job = ApplyJobEntity.builder()
        .candidateId(idCandidate)
        .jobId(idJob)
        .build();

    var appyJob = applyJobRepository.save(Job);
    return appyJob;
  }
}
