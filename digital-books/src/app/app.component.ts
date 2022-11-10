import { Component } from '@angular/core';
import { TokenStorageService } from './services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'digital-books';
  private roles: string[] = [];
  isLoggedIn = false;
  showAuthorProfile = false;
  showReaderProfile = false;
  username?: string;

  constructor(private tokenStorageService: TokenStorageService) { }


  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    console.log(this.isLoggedIn);
    
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAuthorProfile = this.roles.includes('ROLE_AUTHOR');
      this.showReaderProfile = this.roles.includes('ROLE_READER');

      this.username = user.username;
      const obj = {
        users:user,
        roles:this.roles,
        authorprofile:this.showAuthorProfile,
        readerprofile:this.showReaderProfile
      }

      console.log(obj);
      
      
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
