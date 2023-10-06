import { Component, Input } from '@angular/core';
import { Song } from '../models/song';
import { faHeart,faBurger } from '@fortawesome/free-solid-svg-icons';
import { MusicLibraryService } from '../services/music-library.service';
import { Router } from '@angular/router';
import { playlistDetails } from '../models/playlist-details';
@Component({
  selector: 'app-song',
  templateUrl: './song.component.html',
  styleUrls: ['./song.component.css']
})
export class SongComponent {
  faHeart = faHeart;
  faBurger = faBurger;
  @Input() song!: Song;
  isOpen: boolean = false;
  playlistDetails!: playlistDetails[];

  constructor(private musicLibraryService: MusicLibraryService, private router: Router) { }

  ngOnInit(): void {
    this.getPlaylistDetails();
  }

  favoriteSong(songId: number): void {
    this.musicLibraryService.favoriteSong(songId).subscribe(res => {
      window.location.reload();

    });
  }

  openModal(): void {
    this.isOpen = true;
  }

  closeModal(): void {
    this.isOpen = false;
  }

  getPlaylistDetails(): void {
    this.musicLibraryService.getPlaylistDetails().subscribe(res => this.playlistDetails = res);
  }

  addToPlayList(songId: number, playlistId: number): void {
    this.musicLibraryService.addToPlayList(songId, playlistId).subscribe(res => {
      this.closeModal();
      window.location.reload();

    });
  }
  // play
}
