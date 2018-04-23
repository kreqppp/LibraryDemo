package com.kreqppp.demo.service;

import com.kreqppp.demo.dao.AuthorDao;
import com.kreqppp.demo.dao.BookDao;
import com.kreqppp.demo.dto.AuthorDto;
import com.kreqppp.demo.model.Author;
import com.kreqppp.demo.model.Book;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Setter
public class BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    // get all books
    public Iterable<Book> getAllBooks(){
        return bookDao.findAll();
    }

    //get book by id
    public Optional<Book> getBookById(int bookId) {
        return bookDao.findById((long) bookId);
    }

    //save book
    public void saveBook(Book book){
        Author author = book.getAuthor();
        author.addBook(book);
        authorDao.save(author);
    }

    //update book
    public void updateBook(Book book){
        Optional<Book> bookOptional = bookDao.findById(book.getId());
        Book bookUp = bookOptional.get();
        bookUp.setTitle(book.getTitle());
        bookUp.setAuthor(book.getAuthor());
        bookUp.setGenre(book.getGenre());
        bookUp.setYear(book.getYear());
        bookDao.save(bookUp);
    }

    //delete book by id
    public void deleteBookById(int bookId){
        bookDao.deleteById((long) bookId);
    }


}
