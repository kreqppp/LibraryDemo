import {Book} from "../book/book.model";

export class Author{
  constructor(public id: number,
              public firstName: string,
              public lastName: string,
              public books: Book[]){}
}