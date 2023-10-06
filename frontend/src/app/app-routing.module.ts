import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth.gaurd';
import { GenreComponent } from './genre/genre.component';
import { PlaylistComponent } from './playlist/playlist.component';
import { FavouriteComponent } from './favourite/favourite.component';

const routes: Routes = [
  {path:'',redirectTo:'/home',pathMatch: "full"},
  {path:"home", component:HomeComponent,canActivate:[AuthGuard]},
  {path:"login",component:LoginComponent},
  {path:"playlist/:id", component:PlaylistComponent,canActivate:[AuthGuard]},
  {path:"genre/:name", component:GenreComponent,canActivate:[AuthGuard]},
  {path:"favourite/:id", component:FavouriteComponent,canActivate:[AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
