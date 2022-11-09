import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';


const baseUrl = 'http://localhost:9091/api/user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http:HttpClient) { }

  userSignUp(user: {
    username:String,
    email:String,
    password:String,
    roles:[]
  }) {
    return this.http.post(baseUrl + "/sign-up",user,httpOptions);
  }

  userSignIn(user: {
    username:String,
    email:String,
    password:String,
    roles:[]
  }) {
    return this.http.post(baseUrl + "/sign-in",user,httpOptions);
  }
}
