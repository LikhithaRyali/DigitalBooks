import { Component, OnInit } from '@angular/core';
import Books from 'src/app/entities/book';
import { BooksServiceService } from 'src/app/services/books-service.service';

@Component({
  selector: 'app-create-book',
  templateUrl: './create-book.component.html',
  styleUrls: ['./create-book.component.css']
})
export class CreateBookComponent implements OnInit {

  title = "Create new book"
  book:Books = new Books();
  constructor(private bookService: BooksServiceService) { }

  ngOnInit(): void {
  }

  saveBook() {
    const observable = this.bookService.addingBook(this.book);

  observable.subscribe(
    (response:any) => {
      console.log(response);
    },function(error) {
      console.log(error);
      alert("something went wrong please try again");
    }
  )
}
  
}
