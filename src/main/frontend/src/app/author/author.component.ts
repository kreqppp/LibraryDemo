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
    this.authors=[{id: 1, firstName: "Stephan", lastName: "King", books:
        [{id:1, title: "It", author: null, genre: "novel", year: new Date('1986')},
          {id:2, title: "Shinning", author: null, genre: "horror", year: new Date('1977')}]},
                  {id: 2, firstName: "Joanne", lastName: "Rowling", books: []}];
    this.getAllAuthors();
    console.log(this.authors);
  }

  getAllAuthors() {
    this.authorService.getAllAuthors()
      .subscribe(
        data => this.authors = data,
        errorCode =>  this.statusCode = errorCode);
  }

  returnNewArray(elements: number): Array<any>{
    return new Array(elements);
  }

}
