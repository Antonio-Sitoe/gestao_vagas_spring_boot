package com.antonio.gestao_vagas.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class TestUtils {

  public static String objectToJson(Object object) {
    try {
      final ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String generateToken(String idCompany) {
    Algorithm algorithm = Algorithm.HMAC256("dgx@5421");
    String token = JWT.create()
        .withIssuer("gestao_vagas")
        .withExpiresAt(Instant.now().plus(Duration.ofHours(24)))
        .withSubject(idCompany)
        .withClaim("roles", Arrays.asList("COMPANY"))
        .sign(algorithm);
    return token;
  }

}
