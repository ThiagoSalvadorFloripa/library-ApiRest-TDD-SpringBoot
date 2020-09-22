package com.salvador.thiago.libraryapi.controller;

import com.salvador.thiago.libraryapi.dto.BookDTO;
import com.salvador.thiago.libraryapi.model.Book;
import com.salvador.thiago.libraryapi.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookControllerPost {

    private BookService service;
    private ModelMapper modelMapper;

    public BookControllerPost(BookService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@RequestBody BookDTO pyload) {

        Book entity = modelMapper.map(pyload, Book.class);
        entity = service.save(entity);
        return modelMapper.map(entity, BookDTO.class);
    }
}
