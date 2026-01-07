package com.antonio.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antonio.gestao_vagas.modules.company.repositories.JobRepository;
import com.antonio.gestao_vagas.modules.company.entities.JobEntity;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {
  @Autowired
  private JobRepository jobRepository;

  public List<JobEntity> execute(String filter) {
    return this.jobRepository.findByDescriptionContaining(filter);
  }
}
