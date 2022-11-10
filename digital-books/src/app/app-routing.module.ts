import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthorProfileComponent } from './components/author-profile/author-profile.component';
import { CreateBookComponent } from './components/create-book/create-book.component';
import { HomeComponent } from './components/home/home.component';
import { ReaderProfileComponent } from './components/reader-profile/reader-profile.component';
import { SigninComponent } from './components/signin/signin.component';
import { SignupComponent } from './components/signup/signup.component';
import { UpdateBookComponent } from './components/update-book/update-book.component';

const routes: Routes = [
  {path:"home",component:HomeComponent},
  {path:"signin",component:SigninComponent},
  {path:"signup",component:SignupComponent},
  {path:"author",component:AuthorProfileComponent},
  {path:"reader",component:ReaderProfileComponent},
  {path:"newbook",component:CreateBookComponent},
  {path:"updatebook",component:UpdateBookComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
