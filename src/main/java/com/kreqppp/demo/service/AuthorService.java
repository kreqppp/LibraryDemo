package com.kreqppp.demo.service;

import com.kreqppp.demo.dao.AuthorDao;
import com.kreqppp.demo.model.Author;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Setter
public class AuthorService {

    @Autowired
    private AuthorDao authorDao;

    public Iterable<Author> getAllAuthors(){
        return authorDao.findAll();
    }

}
