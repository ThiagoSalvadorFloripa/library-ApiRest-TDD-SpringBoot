package com.salvador.thiago.libraryapi.service;

import com.salvador.thiago.libraryapi.model.Book;
import com.salvador.thiago.libraryapi.service.impl.BookServiceImpl;
import com.salvador.thiago.libraryapi.service.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class) // cria um mine contexto com as classe declaradas
@ActiveProfiles("test") // roda apenas com o perfil de teste
public class BookserviceTest {

    BookService service;

    @MockBean // simular comportamento do repository
    BookRepository repository;

    @BeforeEach // vai ser rodado sempre antes de cada metodo
    public void setUp(){
        this.service = new BookServiceImpl(repository);

    }

    @Test
    @DisplayName("Deve salvar um livro")
    public void saveBooktest(){
        //cenario
        Book book = Book.builder()
                .author("Paulo Freire")
                .title("Alfabeto no Brasil")
                .isbn("12233")
                .build();

        // quando eu execultar o metodo save, deve rotonar uma instancia de livro
        Mockito.when(service.save(book)).thenReturn(
                Book.builder()
                .id(10l)
                .author("Paulo Freire")
                .title("Alfabeto no Brasil")
                .isbn("12233")
                .build());

        // execulção
        Book saveBook = service.save(book);

        // verificação
        assertThat(saveBook.getId()).isNotNull();
        assertThat(saveBook.getAuthor()).isEqualTo("Paulo Freire");
        assertThat(saveBook.getTitle()).isEqualTo("Alfabeto no Brasil");
        assertThat(saveBook.getIsbn()).isEqualTo("12233");

    }
}
