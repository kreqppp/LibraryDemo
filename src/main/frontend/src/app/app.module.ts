import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { BookComponent } from './book/book.component';
import { AuthorComponent } from './author/author.component';
import {BookService} from "./book.service";
import {AuthorService} from "./author.service";
import {RouterModule} from "@angular/router";
import {PopupModule} from "ng2-opd-popup";

const routes = [
  {path: 'booktable', component: BookComponent},
  {path:'authortable', component: AuthorComponent}

];

@NgModule({
  declarations: [
    AppComponent,
    BookComponent,
    AuthorComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(routes),
    PopupModule.forRoot(),
    ReactiveFormsModule
  ],
  providers: [BookService, AuthorService],
  bootstrap: [AppComponent]
})
export class AppModule { }
