import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenStorageService } from './token-storage.service';


const baseUrl = 'http://localhost:9091/author';

@Injectable({
  providedIn: 'root'
})
export class BooksServiceService {


  addingBook(book: {
    bookId:number,
    bookName:String,
    bookImage:String,
    bookPrice:number,
    bookPublisher:String,
    bookCategory:String,
    bookStatus:String,
    bookPublishedDate:Date
  }) {
    return this.http.post(baseUrl + "/add/book",book)
  }

  getMyBooks() {
    return this.http.get(baseUrl + "/mybooks");
  }

  deletingBook(book:any) {
    return this.http.delete(baseUrl + "/delete" + book.bookId )
  }

  updatingBook(book:any) {
    return this.http.put(baseUrl + "/books" + book.bookId,book);
  }

  

  constructor(public http:HttpClient, private author:TokenStorageService) { }
}
