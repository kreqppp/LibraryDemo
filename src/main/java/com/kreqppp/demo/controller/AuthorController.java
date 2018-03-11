package com.kreqppp.demo.controller;

import com.kreqppp.demo.model.Author;
import com.kreqppp.demo.service.AuthorService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Setter
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("all-authors")
    public ResponseEntity<List<Author>> getAllAuthors(){
        Iterable<Author> authors = authorService.getAllAuthors();
        return new ResponseEntity<List<Author>>((List<Author>)authors, HttpStatus.OK);
    }
}
