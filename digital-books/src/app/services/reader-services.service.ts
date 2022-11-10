import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenStorageService } from './token-storage.service';


const baseUrl = 'http://localhost:9091/reader';

@Injectable({
  providedIn: 'root'
})
export class ReaderServicesService {
  readerId = this.reader.getUser().id;

  

  getAllBooks() {
    return this.http.get(baseUrl + "/" + this.readerId + "/books");
  }

  cancelSubcription(book:any) {
    return this.http.delete(baseUrl + "/" + this.readerId +  "/books/" + book.bookId + "/cancel-subscription")
  }

  subscribeBook(subscription : {
    subscriptionId:number,
    userId:number,
    bookId:number,
    subscribedDate:Date
  }) {
    return this.http.post(baseUrl + "/" + this.readerId + subscription.bookId + "/subscribe",subscription)
  }

  

  

  constructor(public http:HttpClient, private reader:TokenStorageService) { }
}
