package com.antonio.gestao_vagas.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtProvider {

  @Value("${spring.security.jwt}")
  private String secretKey;

  public String validateToken(String token) {
    String newToken = token.replace("Bearer ", "");
    try {
      return JWT.require(Algorithm.HMAC256(secretKey))
          .build()
          .verify(newToken).getSubject();
    } catch (JWTVerificationException e) {
      e.printStackTrace();
      return "";
    }

  }
}
