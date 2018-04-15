import {Injectable} from "@angular/core";
import {Http, Response, Headers, RequestOptions, URLSearchParams} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {Author} from "./author/author.model";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";


@Injectable()
export class AuthorService{

  allAuthorsUrl = "http://localhost:8080/user/all-authors";
  authorUrl = "http://localhost:8080/user/author";

  constructor(private http: Http){}


  //fetch all authors
  getAllAuthors(): Observable<Author[]>{
    return this.http.get(this.allAuthorsUrl)
      .map(this.extractData)
      .catch(this.handleError);
  }

  //create author
  createAuthor(author: Author): Observable<number>{
    let header = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: header });
    return this.http.post(this.authorUrl, author, options)
      .map(success => success.status)
      .catch(this.handleError);
  }

  //fetch Author by id
  getAuthorById(authorId: string): Observable<Author> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let params = new URLSearchParams();
    params.append('id', authorId);
    let options = new RequestOptions({ headers: headers, search: params });
    return this.http.get(this.authorUrl, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  //delete author
  deleteAuthorById(authorId: string): Observable<number> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let params = new URLSearchParams();
    params.append('id', authorId);
    let options = new RequestOptions({headers: headers, search: params});
    return this.http.delete(this.authorUrl, options)
      .map(success => success.status)
      .catch(this.handleError);
  }

  //update author
  updateAuthor(author: Author): Observable<number> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.put(this.authorUrl, author, options)
      .map(success => success.status)
      .catch(this.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body;
  }

  private handleError (error: Response | any) {
    console.error(error.message || error);
    return Observable.throw(error.status);
  }
}
