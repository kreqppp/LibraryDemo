import { Component, OnInit } from '@angular/core';
import {AuthorService} from "../author.service";
import {Author} from "./author.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Popup} from "ng2-opd-popup";

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css']
})
export class AuthorComponent implements OnInit {

  authors : Author[];
  statusCode: number;
  processValidation = false;
  requestProcessing = false;
  authorIdToUpdate = null;

  authorForm = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required)
  });

  constructor(private authorService: AuthorService, private popup: Popup) { }

  ngOnInit() {
    this.getAllAuthors();
  }

  AddNewAuthor(){
    this.popup.options = {
      header : "Add new Author",
      confirmBtnContent: "Add"
    };
    this.popup.show();
  }

  //create author
  onAuthorFormSubmit(){
    this.processValidation = true;
    this.preProcessConfigurations();
    let firstName = this.authorForm.get('firstName').value.trim();
    let lastName = this.authorForm.get('lastName').value.trim();
    if (this.authorIdToUpdate === null) {
    let author = new Author(null, firstName, lastName, null);
    this.authorService.createAuthor(author)
      .subscribe(successCode => {
        this.statusCode = successCode;
        this.hidePopup();
        this.ngOnInit();
      },
        errorCode => this.statusCode = errorCode
        );
  }
  else {
      //Update author
      let author= new Author( this.authorIdToUpdate, firstName, lastName, null);
      this.authorService.updateAuthor(author)
        .subscribe(successCode => {
            this.statusCode = successCode;
            this.getAllAuthors();
            this.hidePopup();
          },
          errorCode => this.statusCode = errorCode);
    }
  }


  //get all authors
  getAllAuthors() {
    this.authorService.getAllAuthors()
      .subscribe(
        data => this.authors = data,
        errorCode =>  this.statusCode = errorCode);
  }

  //delete author
  deleteAuthor(authorId: string) {
    this.preProcessConfigurations();
    this.authorService.deleteAuthorById(authorId)
      .subscribe(successCode => {
          this.statusCode = successCode;
          this.getAllAuthors();
        },
        errorCode => this.statusCode = errorCode);
  }

  //update author
  loadAuthorToEdit(authorId: string){
    this.popup.options = {
      header : "Edit Author",
      confirmBtnContent: "Edit"
    };
    this.popup.show();
    this.preProcessConfigurations();
    this.authorService.getAuthorById(authorId)
      .subscribe(author => {
          this.authorIdToUpdate = author.id;
          this.authorForm.setValue({firstName: author.firstName, lastName: author.lastName });
          this.processValidation = true;
          this.requestProcessing = false;
        },
        errorCode =>  this.statusCode = errorCode);
  }


  hidePopup() {
    this.popup.hide();
  }

  preProcessConfigurations(){
    this.statusCode = null;
    this.requestProcessing = true;
  }

}
