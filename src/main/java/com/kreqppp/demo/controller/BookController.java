package com.kreqppp.demo.controller;

import com.kreqppp.demo.model.Book;
import com.kreqppp.demo.service.BookService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Setter
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("all-books")
    public ResponseEntity<List<Book>> getAllBooks(){
        Iterable<Book> books = bookService.getAllBooks();
        return new ResponseEntity<List<Book>>((List<Book>)books, HttpStatus.OK);
    }
}
