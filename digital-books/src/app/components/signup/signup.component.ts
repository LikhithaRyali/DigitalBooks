import { Component, OnInit } from '@angular/core';
//import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Users from 'src/app/entities/users';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  title = "SignUp Form";

  users: Users = new Users();

  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  //validateForm: FormGroup;

  addUser() : void {
    const observable = this.authenticationService.userSignUp(this.users);
    console.log(this.users);
    console.log(observable);
    
    observable.subscribe(
      (response : any) => {
        console.log(response); 
        this.isSuccessful = true;
        this.isSignUpFailed = false;       
      }, function(err) {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
        console.log(err);
        alert("Can't save User at the moment, Check what went wrong and try again");
        
      }
    ) 
  }



  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    

  }

}
