import { Component, OnInit } from '@angular/core';
import {AuthorService} from "../author.service";
import {Author} from "./author.model";

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css']
})
export class AuthorComponent implements OnInit {

  authors : Author[];
  statusCode: number;

  constructor(private authorService: AuthorService) { }

  ngOnInit() {
    this.getAllAuthors();
    console.log(this.authors);
  }

  getAllAuthors() {
    this.authorService.getAllAuthors()
      .subscribe(
        data => this.authors = data,
        errorCode =>  this.statusCode = errorCode);
  }

}
