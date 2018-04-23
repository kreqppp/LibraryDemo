import { Component, OnInit } from '@angular/core';
import {BookService} from "../book.service";
import {Book} from "./book.model";
import {Popup} from "ng2-opd-popup";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Author} from "../author/author.model";
import {AuthorService} from "../author.service";




@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  books : Book[];
  statusCode: number;
  requestProcessing = false;
  bookIdToUpdate = null;
  processValidation = false;


  authors: Author[];
  value: string;

  bookForm = new FormGroup({
    title: new FormControl('', Validators.required),
    author: new FormControl('', Validators.required),
    genre: new FormControl('', Validators.required),
    year: new FormControl('', Validators.required)
  });

  constructor(private bookService: BookService,
              private popup: Popup,
              private authorService: AuthorService) { }

  ngOnInit() {
    this.getAllBooks();
    this.authorService.getAllAuthors()
      .subscribe(
        data => this.authors = data,
        errorCode =>  this.statusCode = errorCode);
  }

  //get all books
  getAllBooks() {
    this.bookService.getAllBooks()
      .subscribe(
        data => this.books = data,
        errorCode =>  this.statusCode = errorCode);
  }

  //create book
  onBookFormSubmit(){
    this.processValidation = true;
    this.preProcessConfigurations();
    let title = this.bookForm.get('title').value.trim();
    let authorDto = this.authors[Number(this.value)-1];
    let genre = this.bookForm.get('genre').value.trim();
    let year = this.bookForm.get('year').value.trim();
    if (this.bookIdToUpdate === null) {
      let book = new Book(null, title, authorDto, genre, year);
      this.bookService.createBook(book)
        .subscribe(successCode => {
          this.statusCode = successCode;
          this.backToCreateBook();
        })
    }
    //update book
    else {
      let book = new Book(this.bookIdToUpdate, title, authorDto, genre, year);
      this.bookService.updateBook(book)
        .subscribe(successCode => {
            this.statusCode = successCode;
            this.backToCreateBook();
          },
          errorCode => this.statusCode = errorCode);

    }

  }

  //update book
  loadBookToEdit(bookId: string){
    this.popup.options = {
      header : "Edit Book",
      confirmBtnContent: "Edit"
    };
    this.popup.show();
    this.preProcessConfigurations();
    this.bookService.getBookById(bookId)
      .subscribe(book => {
          this.bookIdToUpdate = book.id;
          this.bookForm.setValue({title: book.title, author: book.authorDto, genre: book.genre, year: book.year });
          this.processValidation = true;
          this.requestProcessing = false;
        },
        errorCode =>  this.statusCode = errorCode);
  }

  //delete book
  deleteBook(bookId: string) {
    this.preProcessConfigurations();
    console.log(bookId);
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

    };
    this.popup.show();
  }

  hidePopup() {
    this.popup.hide();
  }

  updateObjValue3(author) {
    this.value = (author.value).split(':')[0];
  }

    backToCreateBook() {
      this.bookForm.reset();
      this.processValidation = false;
      this.getAllBooks();
      this.hidePopup();
    }


}
