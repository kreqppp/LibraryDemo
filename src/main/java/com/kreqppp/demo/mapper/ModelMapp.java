package com.kreqppp.demo.mapper;


import com.kreqppp.demo.dto.AuthorDto;
import com.kreqppp.demo.dto.BookDto;
import com.kreqppp.demo.model.Author;
import com.kreqppp.demo.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class ModelMapp implements ModelMapperInterface {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Book bookConvertToEntity(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        return book;
    }

    @Override
    public BookDto bookConvertToDto(Book book) {
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        return bookDto;
    }

    @Override
    public Author authorConvertToEntity(AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        return author;
    }

    @Override
    public AuthorDto authorConvertToDto(Author author) {
        AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);
        return authorDto;
    }

    @Override
    public List<AuthorDto> authorsConvertToDtos(List<Author> authors) {
        List<AuthorDto>  authorDtos = new ArrayList<>();
        for(Author author: authors){
            AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);
            Set<Book> books = author.getBooks();
            Set<BookDto> bookDtos = new HashSet<>();
            for(Book book: books){
                bookDtos.add(modelMapper.map(book, BookDto.class));
            }
            authorDto.setBookDtos(bookDtos
            );
            authorDtos.add(authorDto);
        }
        return authorDtos;
    }


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
