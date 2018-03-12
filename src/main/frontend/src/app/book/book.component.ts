import { Component, OnInit } from '@angular/core';
import {BookService} from "../book.service";
import {Book} from "./book.model";


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
    this.getAllBooks();
  }

  getAllBooks() {
    this.bookService.getAllBooks()
      .subscribe(
        data => this.books = data,
        errorCode =>  this.statusCode = errorCode);
  }

}
