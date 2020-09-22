package com.salvador.thiago.libraryapi.service.impl;

import com.salvador.thiago.libraryapi.model.Book;
import com.salvador.thiago.libraryapi.service.BookService;
import com.salvador.thiago.libraryapi.service.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }
}
