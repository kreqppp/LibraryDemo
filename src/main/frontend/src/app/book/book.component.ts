import { Component, OnInit } from '@angular/core';
import {BookService} from "../book.service";
import {Book} from "./book.model";
import {Author} from "../author/author.model";

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  books : Book[];
  statusCode: number;

  constructor(private bookService: BookService) { }

  ngOnInit() {
    this.books = [{id:1, title: "It", author: new Author(1,"Stephan", "King", []), genre: "novel", year: new Date('1986')},
      {id:2, title: "Shinning", author: null, genre:"horror", year: new Date('1977')}]
    this.getAllBooks();
  }

  getAllBooks() {
    this.bookService.getAllBooks()
      .subscribe(
        data => this.books = data,
        errorCode =>  this.statusCode = errorCode);
  }

}
