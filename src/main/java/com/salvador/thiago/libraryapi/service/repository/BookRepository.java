package com.salvador.thiago.libraryapi.service.repository;

import com.salvador.thiago.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import sun.rmi.runtime.Log;

public interface BookRepository extends JpaRepository<Book, Long> {
}
