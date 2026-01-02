package com.antonio.gestao_vagas.modules.candidate.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.antonio.gestao_vagas.modules.candidate.CandidateRepository;
import com.antonio.gestao_vagas.modules.candidate.dto.AuthCandidateDTO;
import com.antonio.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class AuthCandidateUseCase {
  @Value("${spring.security.jwt.candidate}")
  private String secretKey;

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthCandidateResponseDTO execute(AuthCandidateDTO authCandidateDTO) {

    var candidate = this.candidateRepository.findByUsername(authCandidateDTO.username()).orElseThrow(() -> {
      throw new UsernameNotFoundException("Username/Password incorrect");
    });

    Boolean isPasswordValid = passwordEncoder.matches(authCandidateDTO.password(), candidate.getPassword());

    if (!isPasswordValid) {
      throw new UsernameNotFoundException("Username/Password incorrect");
    }

    String token = JWT.create()
        .withIssuer("gestao_vagas")
        .withExpiresAt(Instant.now().plus(Duration.ofHours(24)))
        .withSubject(candidate.getId().toString())
        .withClaim("roles", Arrays.asList("CANDIDATE"))
        .sign(Algorithm.HMAC256(secretKey));

    return AuthCandidateResponseDTO.builder()
        .access_token(token)
        .expire_in(Instant.now().plus(Duration.ofMillis(10)).toEpochMilli())
        .build();
  }

}
