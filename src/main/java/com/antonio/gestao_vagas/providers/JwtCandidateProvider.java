package com.antonio.gestao_vagas.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtCandidateProvider {
  @Value("${security.token.secret.candidate}")
  private String secretKey;

  public DecodedJWT validateToken(String token) {
    token = token.replace("Bearer", "");
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    try {
      DecodedJWT tokenDecoded = JWT.require(algorithm).build().verify(token);
      return tokenDecoded;
    } catch (Exception e) {
      System.err.println(e);
      return null;
    }
  }
}
