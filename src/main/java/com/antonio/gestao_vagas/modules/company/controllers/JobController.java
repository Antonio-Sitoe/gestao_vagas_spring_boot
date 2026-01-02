package com.antonio.gestao_vagas.modules.company.controllers;

import java.util.UUID;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.antonio.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.antonio.gestao_vagas.modules.company.entities.JobEntity;
import com.antonio.gestao_vagas.modules.company.useCases.CreateJobUseCase;

@RestController
@RequestMapping("/company/job")
public class JobController {

  @Autowired
  private CreateJobUseCase createJobUseCase;

  @PostMapping("")
  @PreAuthorize("hasRole('COMPANY')")
  public JobEntity create(@Valid @RequestBody CreateJobDTO job, HttpServletRequest request) {
    var companyId = request.getAttribute("company_id");
    JobEntity jobEntity = JobEntity.builder()
        .benefits(job.getBenefits())
        .companyId(UUID.fromString(companyId.toString()))
        .description(job.getDescription())
        .level(job.getLevel())
        .build();

    return this.createJobUseCase.execute(jobEntity);
  }
}
