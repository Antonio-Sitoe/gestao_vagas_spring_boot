package com.antonio.gestao_vagas.modules.company.useCases;

import com.auth0.jwt.JWT;
import java.time.Instant;
import java.time.Duration;
import com.auth0.jwt.algorithms.Algorithm;
import javax.naming.AuthenticationException;
import com.antonio.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.antonio.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AuthCompanyUseCase {

  @Value("${spring.security.jwt}")
  private String secretKey;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
        .orElseThrow(() -> {
          throw new UsernameNotFoundException("Company not found");
        });

    Boolean isPasswordValid = passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

    if (!isPasswordValid) {
      throw new AuthenticationException("Invalid credentials");
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    String token = JWT.create()
        .withIssuer("gestao_vagas")
        .withExpiresAt(Instant.now().plus(Duration.ofHours(24)))
        .withSubject(company.getUsername())
        .sign(algorithm);
    return token;
  }
}
