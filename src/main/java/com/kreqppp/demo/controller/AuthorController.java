package com.kreqppp.demo.controller;

import com.kreqppp.demo.model.Author;
import com.kreqppp.demo.model.Book;
import com.kreqppp.demo.service.AuthorService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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
        //here too... read in BookController
        for(Author author : authors){
            for(Book book : author.getBooks()){
                book.setAuthor(null);
            }
        }
        return new ResponseEntity<List<Author>>((List<Author>)authors, HttpStatus.OK);
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
