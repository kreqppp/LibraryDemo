import {Author} from "../author/author.model";

export class Book{
  constructor(public id: number,
              public title: string,
              public authorDto: Author,
              public genre: string,
              public year: Date){}


}
