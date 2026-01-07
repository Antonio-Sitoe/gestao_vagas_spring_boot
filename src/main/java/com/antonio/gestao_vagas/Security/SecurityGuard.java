package com.antonio.gestao_vagas.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration()
@EnableMethodSecurity
public class SecurityGuard {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> {
          auth.requestMatchers("/candidate").permitAll()
              .requestMatchers("/company").permitAll()
              .requestMatchers("/company/auth").permitAll()
              .requestMatchers("/candidate/auth").permitAll()
              .requestMatchers("/swagger-ui/**").permitAll()
              .requestMatchers("/swagger-ui.html").permitAll()
              .requestMatchers("/v3/api-docs/**").permitAll()
              .requestMatchers("/swagger-resources/**").permitAll()
              .requestMatchers("/webjars/**").permitAll();
          auth.anyRequest().authenticated();
        });
    return http.build();
  }
}
