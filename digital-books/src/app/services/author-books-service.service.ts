import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenStorageService } from './token-storage.service';


const baseUrl = 'http://localhost:9091/author';

@Injectable({
  providedIn: 'root'
})
export class AuthorBooksServiceService {

  authorId = this.author.getUser().id;

  saveBook(book: {
    bookId:number,
    bookName:String,
    bookImage:String,
    bookPrice:number,
    bookPublisher:String,
    bookCategory:String,
    bookStatus:String,
    bookPublishedDate:Date
  }) {
    return this.http.post(baseUrl + "/" + this.authorId + "/add/book",book)
  }

  getMyBooks() {
    return this.http.get(baseUrl + "/" + this.authorId + "/mybooks");
  }

  deleteBook(book:any) {
    return this.http.delete(baseUrl + "/" + this.authorId + )
  }

  constructor(public http:HttpClient, private author:TokenStorageService) { }
}
