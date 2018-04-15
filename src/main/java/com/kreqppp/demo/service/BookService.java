package com.kreqppp.demo.service;

import com.kreqppp.demo.dao.BookDao;
import com.kreqppp.demo.model.Book;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Setter
public class BookService {

    @Autowired
    private BookDao bookDao;

    // fetch all books
    public Iterable<Book> getAllBooks(){
        return bookDao.findAll();
    }

    public void deleteBookById(int bookId){
        bookDao.deleteById((long) bookId);
    }
}
