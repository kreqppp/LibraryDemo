import {Injectable} from "@angular/core";
import {Http, Response, Headers, URLSearchParams, RequestOptions, RequestOptionsArgs} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {Book} from "./book/book.model";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';


@Injectable()
export class BookService{

  allBooksUrl = "http://localhost:8080/all-books";
  bookUrl = "http://localhost:8080/book";

  constructor(public http: Http){}

  // fetch all books
  getAllBooks(): Observable<Book[]> {
    return this.http.get(this.allBooksUrl)
      .map(this.extractData)
      .catch(this.handleError);
  }

  //create book
  createBook(book: Book):Observable<number> {
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers});
    return this.http.post(this.bookUrl, book, options)
      .map(success => success.status)
      .catch(this.handleError);
  }
  //fetch book by id
  getBookById(bookId: string): Observable<Book[]> {
    let myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');
    let myParams = new URLSearchParams();
    myParams.append('id', bookId);
    let options = new RequestOptions(<RequestOptionsArgs>{headers: myHeaders, params: myParams});
    return this.http.get(this.bookUrl, options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  //update book
  updateBook(book: Book):Observable<number> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.put(this.bookUrl, book, options)
      .map(success => success.status)
      .catch(this.handleError);
  }

  //delete book
  deleteBookById(bookId: string): Observable<number> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let params = new URLSearchParams();
    params.set('id', bookId);
    let options = new RequestOptions(<RequestOptionsArgs>{headers: headers, params: params});
    console.log(options);
    return this.http.delete(this.bookUrl, options)
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
