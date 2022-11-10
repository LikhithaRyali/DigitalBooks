import { Component, OnInit } from '@angular/core';
import Users from 'src/app/entities/users';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  title = "Sign-In Form"

  
  users:Users = new Users();
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';


  


  constructor(private authenticationService: AuthenticationService, private toekenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if(this.toekenStorage.getToken()) {
      this.isLoggedIn = true;
      this.users.roles = this.toekenStorage.getUser().roles;
    }
  }

  userSignIn() : void {
    const observable = this.authenticationService.userSignIn(this.users);
   console.log(this.users);
    console.log(observable);
    
    observable.subscribe(
      (response : any) => {
        console.log(response);
        this.toekenStorage.saveToken(response.accessToken) ;
        this.toekenStorage.saveUser(response);

        this.isLoginFailed = false;
        this.isLoggedIn = true;   
        this.users.roles = this.toekenStorage.getUser().roles;
        // this.reloadPage();  
      }, function(err) {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
        console.log(err);
        alert("Can't Login at the moment, try again");
        
      }
    ) 
  }


  // reloadPage() : void {
  //   this.toekenStorage.signOut();
  //   window.location.reload();
  // }
}
