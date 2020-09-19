package com.salvador.thiago.libraryapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class) // cria um mine contexto com as classe declaradas
@ActiveProfiles("test") // roda apenas com o perfil de teste
@WebMvcTest
@AutoConfigureMockMvc // configura objeto e faz as requisições
public class BookControllerPOSTTest {

    static String URL_BASE = "/api/books";

    @Autowired
    MockMvc mvc; // moca as requisiçoes

    @Test
    @DisplayName("Deve criar um livro com sucesso")
    public void createBookTest() throws Exception{
        // cenario
        String json = new ObjectMapper().writeValueAsString(null);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json); // corpo
        // execucao
        mvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("title").value("Não espere pelo epitafio"))
                .andExpect(jsonPath("author").value("Mario Cortela"))
                .andExpect(jsonPath("isbn").value("1233445"));
    }
}
