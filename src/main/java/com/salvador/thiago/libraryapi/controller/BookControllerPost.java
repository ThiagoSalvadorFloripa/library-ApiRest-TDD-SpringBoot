package com.salvador.thiago.libraryapi.controller;

import com.salvador.thiago.libraryapi.dto.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookControllerPost {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor("Mario Cortela");
        bookDTO.setTitle("Não espere pelo epitafio");
        bookDTO.setIsbn("1233445");
        bookDTO.setId(1l);
        return bookDTO;
    }
}
