import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {Author} from "./author/author.model";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";


@Injectable()
export class AuthorService{

  allAuthorsUrl = "http://localhost:8080/all-authors";

  constructor(private http: Http){}

  getAllAuthors(): Observable<Author[]>{
    return this.http.get(this.allAuthorsUrl)
      .map(this.extractData)
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
