package com.kreqppp.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kreqppp.demo.model.Book;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class AuthorDto {

    @NotNull
    private Long id;

    @NotNull
    private String firstName;

    private String lastName;


    private Set<BookDto> bookDtos = new HashSet<>();
}
