import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-reader-profile',
  templateUrl: './reader-profile.component.html',
  styleUrls: ['./reader-profile.component.css']
})
export class ReaderProfileComponent implements OnInit {
  content?: String


  constructor(private userService:UserService) { }

  ngOnInit(): void {
    const observable = this.userService.getReaderProfile();

    observable.subscribe(
      (response) => {
        this.content = response;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    )
  }


}
