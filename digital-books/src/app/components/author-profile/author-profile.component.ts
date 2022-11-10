import { Component, OnInit } from '@angular/core';
import Books from 'src/app/entities/book';
import { BooksServiceService } from 'src/app/services/books-service.service';

@Component({
  selector: 'app-author-profile',
  templateUrl: './author-profile.component.html',
  styleUrls: ['./author-profile.component.css']
})
export class AuthorProfileComponent implements OnInit {

  deletingBook(book,index) {
    const observable = this.bookService.deletingBook(book)
    observable.subscribe(
      (response) => {
        console.log(response);
        this.books.splice(index,1)
        
      }
    )
  }

  books : Books[]=[]


  constructor(private bookService: BooksServiceService) { }

  ngOnInit(): void {
    const promise = this.bookService.getMyBooks();
    promise.subscribe(
      (response) => {
        console.log(response);
        this.books = response as Books[]
        
      }
    )
  }

}

