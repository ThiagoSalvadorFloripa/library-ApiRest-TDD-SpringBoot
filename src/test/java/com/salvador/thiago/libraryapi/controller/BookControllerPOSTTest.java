package com.salvador.thiago.libraryapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salvador.thiago.libraryapi.dto.BookDTO;
import com.salvador.thiago.libraryapi.model.Book;
import com.salvador.thiago.libraryapi.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @MockBean // instacia mocada
    BookService service;

    @Test
    @DisplayName("Deve criar um livro com sucesso")
    public void createBookTest() throws Exception{

        // cenario
        BookDTO bookDTO = BookDTO.builder()
                .author("Paulo Freire")
                .title("Alfabeto no Brasil")
                .isbn("12233")
                .build();

        //para objeto de retorno
        Book saveBook = Book.builder()
                .id(10l)
                .author("Paulo Freire")
                .title("Alfabeto no Brasil")
                .isbn("12233")
                .build();

        // simulando comportamento do metodo save
        BDDMockito.given(service.save(Mockito.any(Book.class))).willReturn(saveBook);

        String json = new ObjectMapper().writeValueAsString(bookDTO);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json); // corpo

        // execucao e assertivos
        mvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("title").value(bookDTO.getTitle()))
                .andExpect(jsonPath("author").value(bookDTO.getAuthor()))
                .andExpect(jsonPath("isbn").value(bookDTO.getIsbn()));
    }
}
