package com.kreqppp.demo.service;

import com.kreqppp.demo.dao.AuthorDao;
import com.kreqppp.demo.model.Author;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Setter
public class AuthorService {

    @Autowired
    private AuthorDao authorDao;

    //get author by id
    public Optional<Author> getAuthorById(int authorId){
        return authorDao.findById((long) authorId);
    }

    //get all authors from database
    public Iterable<Author> getAllAuthors(){
        return authorDao.findAll();
    }

    //save author in database
    public void saveAuthor(Author author){
        authorDao.save(author);
    }

    //delete author
    public void deleteAuthorById(int id){
        authorDao.deleteById((long) id);
    }

    //update author
    public void updateAuthor(Author author){
        Optional<Author> authorOptional = authorDao.findById(author.getId());
        Author authorUp = authorOptional.get();
        authorUp.setFirstName(author.getFirstName());
        authorUp.setLastName(author.getLastName());
        authorDao.save(authorUp);
    }

}
