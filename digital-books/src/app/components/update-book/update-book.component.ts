import { Component, OnInit } from '@angular/core';
import Books from 'src/app/entities/book';
import { BooksServiceService } from 'src/app/services/books-service.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {

  title = "Update Book"
  book:Books = new Books();
  constructor(private bookService: BooksServiceService) { }

  ngOnInit(): void {
  }

  updateBook() {
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