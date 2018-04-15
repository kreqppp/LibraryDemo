import { Component, OnInit } from '@angular/core';
import {BookService} from "../book.service";
import {Book} from "./book.model";
import {Popup} from "ng2-opd-popup";


@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  books : Book[];
  statusCode: number;
  requestProcessing = false;

  constructor(private bookService: BookService, private popup: Popup) { }

  ngOnInit() {
    this.getAllBooks();
  }

  getAllBooks() {
    this.bookService.getAllBooks()
      .subscribe(
        data => this.books = data,
        errorCode =>  this.statusCode = errorCode);
  }



  //delete book
  deleteBook(bookId: string) {
    this.preProcessConfigurations();
    this.bookService.deleteBookById(bookId)
      .subscribe(successCode => {
          this.statusCode = successCode;
          this.getAllBooks();
        },
        errorCode => this.statusCode = errorCode);
  }

  preProcessConfigurations() {
    this.statusCode = null;
    this.requestProcessing = true;
  }

  addNewBook(){
    this.popup.options = {
      header: "Add new book",
      color: "#9a94ee",
      animation: "fadeInDown",
      widthProsentage: 60

    }
    this.popup.show();
  }
}
