package com.antonio.gestao_vagas.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtProvider {

  @Value("${spring.security.jwt}")
  private String secretKey;

  public DecodedJWT validateToken(String token) {
    String newToken = token.replace("Bearer ", "");
    try {
      var tokenDecoded = JWT.require(Algorithm.HMAC256(secretKey))
          .build()
          .verify(newToken);

      return tokenDecoded;
    } catch (JWTVerificationException e) {
      e.printStackTrace();
      return null;
    }
  }
}
