import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RequestInterceptor } from './request.inceptor';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LibraryComponent } from './library/library.component';
import { OptionsComponent } from './options/options.component';
import { SearchComponent } from './search/search.component';
import { SongsComponent } from './songs/songs.component';
import { SongComponent } from './song/song.component';
import { PlaylistComponent } from './playlist/playlist.component';
import { GenreComponent } from './genre/genre.component';
import { FavouriteComponent } from './favourite/favourite.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    NavbarComponent,
    LibraryComponent,
    OptionsComponent,
    SearchComponent,
    SongsComponent,
    SongComponent,
    PlaylistComponent,
    GenreComponent,
    FavouriteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    FontAwesomeModule,
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: RequestInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
