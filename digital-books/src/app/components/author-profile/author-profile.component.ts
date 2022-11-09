import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-author-profile',
  templateUrl: './author-profile.component.html',
  styleUrls: ['./author-profile.component.css']
})
export class AuthorProfileComponent implements OnInit {

  content?: String


  constructor(private userService:UserService) { }

  ngOnInit(): void {
    const observable = this.userService.getAuthorProfile();

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
