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
    public Author updateAuthor(Author author){
        System.out.println(author.getId() + " " + author.getFirstName() + " " + author.getLastName());
        Optional<Author> auth = authorDao.findById(author.getId());
        Author author1 = auth.get();
        author1.setFirstName(author.getFirstName());
        author1.setLastName(author.getLastName());
        authorDao.save(author1);
        return author1;
    }

}
