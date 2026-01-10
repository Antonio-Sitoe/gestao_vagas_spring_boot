package com.antonio.gestao_vagas.modules.company.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.context.WebApplicationContext;

import com.antonio.gestao_vagas.GestaoVagasApplication;
import com.antonio.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.antonio.gestao_vagas.utils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;

@SpringBootTest(classes = GestaoVagasApplication.class)
public class CreateJobController {

  private MockMvc mvc;

  @Autowired
  private WebApplicationContext context;

  @BeforeEach
  public void setup() {
    mvc = MockMvcBuilders.webAppContextSetup(context)
        .apply(SecurityMockMvcConfigurers.springSecurity())
        .build();
  }

  @Test
  public void shouldBeCreateNewJob() throws Exception {

    var createJobDto = CreateJobDTO.builder()
        .benefits(("BENEFITS_TET"))
        .description("hEELO WIRD")
        .level("level Teste")
        .build();

    var result = mvc.perform(
        MockMvcRequestBuilders.post("/company/job")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectToJson(createJobDto))
            .header("Authorization", "Bearer " + TestUtils.generateToken("1234567890")))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());

    System.out.println(result);
  }

  private static String objectToJson(Object object) {
    try {
      final ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
