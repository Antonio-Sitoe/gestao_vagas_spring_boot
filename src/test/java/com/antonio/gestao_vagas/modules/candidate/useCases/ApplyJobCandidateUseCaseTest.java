package com.antonio.gestao_vagas.modules.candidate.useCases;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.antonio.gestao_vagas.exception.UserNotFoundException;
import com.antonio.gestao_vagas.modules.candidate.CandidateEntity;
import com.antonio.gestao_vagas.modules.candidate.CandidateRepository;
import com.antonio.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import com.antonio.gestao_vagas.modules.company.entities.JobEntity;
import com.antonio.gestao_vagas.modules.company.repositories.JobRepository;
import com.antonio.gestao_vagas.modules.candidate.repository.ApplyJobRepository;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

  @InjectMocks
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @Mock
  private CandidateRepository candidateRepository;

  @Mock
  private JobRepository jobRepository;

  @Mock
  private ApplyJobRepository applyJobRepository;

  @Test
  @DisplayName("Should not be able to apply to a job with candidate not found")
  public void sholdNotBeAbleToApplyToAJobWithCandidateNotFound() {

    try {
      applyJobCandidateUseCase.execute(UUID.randomUUID(), UUID.randomUUID());
    } catch (Exception e) {
      assertThat(e).isInstanceOf(UserNotFoundException.class);
    }
  }

  @Test
  @DisplayName("Should not be able to apply to a job with job not found")
  public void shouldNotBeAbleToApplyJoBWhenJobNotFound() {
    var idCandidate = UUID.randomUUID();

    CandidateEntity candidate = new CandidateEntity();
    candidate.setId(idCandidate);

    when(this.candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

    try {
      applyJobCandidateUseCase.execute(idCandidate, null);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(UserNotFoundException.class);
    }
  }

  @Test
  public void shouldBeAbleToCreateANewApplyJob() {
    var idCandidate = UUID.randomUUID();
    var jobId = UUID.randomUUID();

    var ApplyJob = ApplyJobEntity.builder()
        .candidateId(idCandidate)
        .jobId(jobId).build();

    var applyJoBcreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

    ApplyJob.setId(UUID.randomUUID());

    when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
    when(jobRepository.findById(jobId)).thenReturn(Optional.of(new JobEntity()));
    when(applyJobRepository.save(null)).thenReturn(applyJoBcreated);

    ApplyJobEntity result = applyJobCandidateUseCase.execute(idCandidate, jobId);

    assertThat(result).hasFieldOrProperty("id");
    assertNull(result.getId());
  }
}
