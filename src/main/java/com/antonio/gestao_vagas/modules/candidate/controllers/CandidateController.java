package com.antonio.gestao_vagas.modules.candidate.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antonio.gestao_vagas.modules.candidate.CandidateEntity;
import com.antonio.gestao_vagas.modules.candidate.useCases.ApplyJobCandidateUseCase;
import com.antonio.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import com.antonio.gestao_vagas.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import com.antonio.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidate", description = "Endpoints para o candidato")
public class CandidateController {

  @Autowired
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @Autowired
  private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;

  @PostMapping("")
  @Operation(summary = "Cria um novo candidato", description = "Endpoint para registrar um novo candidato no sistema.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Candidato criado com sucesso"),
      @ApiResponse(responseCode = "400", description = "Erro ao criar candidato", content = @Content(schema = @Schema(implementation = String.class)))
  })
  public ResponseEntity<Object> create(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do candidato para cadastro", required = true, content = @Content(schema = @Schema(implementation = CandidateEntity.class))) @Valid @RequestBody CandidateEntity candidate) {
    try {
      var candidateSaved = this.createCandidateUseCase.execute(candidate);
      return ResponseEntity.status(HttpStatus.CREATED).body(candidateSaved);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping("/")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(summary = "Perfil do candidato autenticado", description = "Retorna o perfil do candidato atualmente autenticado no sistema.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Perfil recuperado com sucesso"),
      @ApiResponse(responseCode = "400", description = "Erro ao buscar perfil", content = @Content(schema = @Schema(implementation = String.class)))
  })
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<Object> profile(HttpServletRequest request) {
    var idCandidate = request.getAttribute("candidate_id");
    try {
      var profile = this.profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));
      return ResponseEntity.status(HttpStatus.OK).body(profile);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping("/jobs")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Tag(name = "Candidate", description = "Endpoints para o candidato")
  @Operation(summary = "Listagem de vagas disponíveis para o candidato", description = "Lista todas as vagas filtradas através do parâmetro de busca para o candidato autenticado.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Vagas encontradas com sucesso"),
      @ApiResponse(responseCode = "400", description = "Erro ao buscar vagas", content = @Content(schema = @Schema(implementation = String.class)))
  })
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<Object> findJobsByFilter(
      @Parameter(description = "Filtro para busca das vagas (por exemplo: nível, área, etc.)", required = true, example = "Java") @RequestParam String filter) {
    try {
      var jobs = this.listAllJobsByFilterUseCase.execute(filter);
      return ResponseEntity.status(HttpStatus.OK).body(jobs);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PostMapping("/job/apply")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(summary = "Inscricao do candidato para uma vaga", description = "Funcao responsavel para a inscricao de um candidato a uma vaga.")
  @SecurityRequirement(name = "bearerAuth")
  public ResponseEntity<Object> applyJob(HttpServletRequest request, @RequestBody UUID idJob) {
    var idCandidate = request.getAttribute("candidate_id");
    try {
      var result = this.applyJobCandidateUseCase.execute(UUID.fromString(idCandidate.toString()), idJob);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

}
