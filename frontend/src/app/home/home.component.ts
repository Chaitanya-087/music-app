import { Component } from '@angular/core';
import { MusicLibraryService } from '../services/music-library.service';
import { Song } from '../models/song';
import { AlbumDetails } from '../models/album-details';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  recentSongs!: Song[]
  genres!: string[]
  albumDetails!: AlbumDetails[]
  constructor(private musicLibraryService: MusicLibraryService) { }

  ngOnInit() {
    this.getRecent();
    this.getGenres();
    this.getAlbumDetails();
  }

  getRecent() {
    this.musicLibraryService.getRecent().subscribe(res => this.recentSongs = res);
  }

  getGenres() {
    this.musicLibraryService.getGenres().subscribe(res => this.genres = res);
  }

  getAlbumDetails() {
    this.musicLibraryService.getAlbumsDetails().subscribe(res => this.albumDetails = res);
  }
}
