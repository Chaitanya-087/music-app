import { Component } from '@angular/core';
import { MusicLibraryService } from '../services/music-library.service';
import { Song } from '../models/song';

@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent {
  constructor(private musicLibraryService: MusicLibraryService) { }
  songs: Song[] = [];
  ngOnInit(): void {
    this.getFavoriteSongs();
  }

  getFavoriteSongs() {
    this.musicLibraryService.getfavoriteSongs().subscribe(res => this.songs = res);
  }
}
