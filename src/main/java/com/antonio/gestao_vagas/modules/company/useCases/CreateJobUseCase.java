package com.antonio.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antonio.gestao_vagas.modules.company.entities.JobEntity;
import com.antonio.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {

  @Autowired
  private JobRepository jobRepository;

  public JobEntity execute(JobEntity job) {
    return this.jobRepository.save(job);
  }
}
