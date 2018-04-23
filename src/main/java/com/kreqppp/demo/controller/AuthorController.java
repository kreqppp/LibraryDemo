package com.kreqppp.demo.controller;

import com.kreqppp.demo.dto.AuthorDto;
import com.kreqppp.demo.dto.BookDto;
import com.kreqppp.demo.mapper.ModelMapp;
import com.kreqppp.demo.model.Author;
import com.kreqppp.demo.model.Book;
import com.kreqppp.demo.service.AuthorService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Date;
import java.util.*;


@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
@Setter
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ModelMapp modelMapp;

    //get author by Id
    @GetMapping("author")
    public ResponseEntity<AuthorDto> getAuthorById(@RequestParam("id") String id) {
        Optional<Author> author = authorService.getAuthorById(Integer.parseInt(id));
        Author author1 = author.get();
        return new ResponseEntity<AuthorDto>(modelMapp.authorConvertToDto(author1), HttpStatus.OK);
    }

    //get all authors
    @GetMapping("all-authors")
    public ResponseEntity<List<AuthorDto>> getAllAuthorDtos(){
        Iterable<Author> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(modelMapp.authorsConvertToDtos((List<Author>) authors), HttpStatus.OK);
    }

    //create Author
    @PostMapping("author")
    public ResponseEntity<Void> createAuthor(@RequestBody AuthorDto authorDto, UriComponentsBuilder builder) {
        authorService.saveAuthor(modelMapp.authorConvertToEntity(authorDto));
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //update author
    @PutMapping("author")
    public ResponseEntity<AuthorDto> updateAuthor(@RequestBody AuthorDto authorDto) {
        Author author = modelMapp.authorConvertToEntity(authorDto);
        authorService.updateAuthor(author);
        return new ResponseEntity<AuthorDto>(authorDto, HttpStatus.OK);
    }

    //delete Author
    @DeleteMapping("author")
    public ResponseEntity<Void> deleteBook(@RequestParam("id") String id) {
        System.out.println(id);
        authorService.deleteAuthorById(Integer.parseInt(id));
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    // insert test date into database
    @RequestMapping("test")
    public String test (ModelMap model)
    {
        Author author = new Author();
        author.setFirstName("Stephen");
        author.setLastName("King");

        Author author1 = new Author();
        author1.setFirstName("Joanne ");
        author1.setLastName("Rowling");
        System.out.println("Authors created");

        Book book1 = new Book();
        book1.setTitle("It");
        book1.setAuthor(author);
        book1.setGenre("novel");
        book1.setYear(Date.valueOf("1986-02-01"));

        Book book2 = new Book();
        book2.setTitle("Shining");
        book2.setAuthor(author);
        book2.setGenre("horror");
        book2.setYear(Date.valueOf("1977-02-01"));

        Book book3 = new Book();
        book3.setTitle("Carrie");
        book3.setAuthor(author);
        book3.setGenre("novel");
        book3.setYear(Date.valueOf("1974-02-01"));

        Book book4 = new Book("Harry potter and the philosopher's stone",
                author1,"fantasy",Date.valueOf("1997-02-01"));
        System.out.println("Books created");

        author.addBook(book1);
        author.addBook(book2);
        author.addBook(book3);
        author1.addBook(book4);
        System.out.println("books added");

        authorService.saveAuthor(author);
        authorService.saveAuthor(author1);
        System.out.println("Authors saved");
        return "test information inserted";
    }
}
