package com.salvador.thiago.libraryapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
}
