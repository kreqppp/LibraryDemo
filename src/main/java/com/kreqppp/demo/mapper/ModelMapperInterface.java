package com.kreqppp.demo.mapper;

import com.kreqppp.demo.dto.AuthorDto;
import com.kreqppp.demo.dto.BookDto;
import com.kreqppp.demo.model.Author;
import com.kreqppp.demo.model.Book;

import java.util.List;

public interface ModelMapperInterface {

    Book bookConvertToEntity(BookDto bookDto);
    BookDto bookConvertToDto(Book book);
    Author authorConvertToEntity(AuthorDto authorDto);
    AuthorDto authorConvertToDto(Author author);
    List<AuthorDto> authorsConvertToDtos(List<Author> authors);
    List<BookDto> booksConvertToDtos(List<Book> books);

}
