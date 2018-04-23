package com.kreqppp.demo.controller;

import com.kreqppp.demo.dto.BookDto;
import com.kreqppp.demo.mapper.ModelMapp;
import com.kreqppp.demo.model.Author;
import com.kreqppp.demo.model.Book;
import com.kreqppp.demo.service.BookService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("user")
@Setter
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapp modelMapp;

    //get all books
    @GetMapping("all-books")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        Iterable<Book> books = bookService.getAllBooks();
        return new ResponseEntity<List<BookDto>>((modelMapp.booksConvertToDtos((List<Book>)books)), HttpStatus.OK);
    }

    //get book by id
    @GetMapping("book")
    public ResponseEntity<BookDto> getBookById(@RequestParam("id") String id) {
        Optional<Book> bookOptional = bookService.getBookById(Integer.parseInt(id));
        Book book = bookOptional.get();
        return new ResponseEntity<BookDto>(modelMapp.bookConvertToDto(book), HttpStatus.OK);
    }

    //create book
    @PostMapping("book")
    public ResponseEntity<Void> createBook(@RequestBody BookDto bookDto, UriComponentsBuilder builder) {
        Book book = modelMapp.bookConvertToEntity(bookDto);
        Author author = modelMapp.authorConvertToEntity(bookDto.getAuthorDto());
        author.addBook(book);
        book.setAuthor(author);
        bookService.saveBook(book);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //update book
    @PutMapping("book")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto) {
        Book book = modelMapp.bookConvertToEntity(bookDto);
        bookService.updateBook(book);
        return new ResponseEntity<BookDto>(bookDto, HttpStatus.OK);
    }

    //delete book
    @DeleteMapping("book")
    public ResponseEntity<Void> deleteBook(@RequestParam("id") String id) {
        System.out.println(id);
        bookService.deleteBookById(Integer.parseInt(id));
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
