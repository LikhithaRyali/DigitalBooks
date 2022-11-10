import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ReaderServicesService } from 'src/app/services/reader-services.service';

@Component({
  selector: 'app-reader-profile',
  templateUrl: './reader-profile.component.html',
  styleUrls: ['./reader-profile.component.css']
})
export class ReaderProfileComponent implements OnInit {

  title = "My Books"

  cancelSubscription(book,index) {
    const observable = this.readerService.cancelSubcription(book)
    observable.subscribe(
      (response) => {
        console.log(response);
        this.subscription.splice(index,1)
        
      }
    )
  }

  subscription : Subscription[]=[]


  constructor(private readerService: ReaderServicesService) { }

  ngOnInit(): void {
    const promise = this.readerService.getAllBooks();
    promise.subscribe(
      (response) => {
        console.log(response);
        this.subscription = response as Subscription[]
        
      }
    )
  }
}
