import { Component } from '@angular/core';
import { faLayerGroup } from '@fortawesome/free-solid-svg-icons';
import { MusicLibraryService } from '../services/music-library.service';
import { AuthService } from '../services/auth.service';
@Component({
  selector: 'app-library',
  templateUrl: './library.component.html',
  styleUrls: ['./library.component.css']
})
export class LibraryComponent {
  faLibrary = faLayerGroup;
  playlistDetails: any[] = [];
  constructor(private musicLibraryService: MusicLibraryService, public authService: AuthService) { }

  ngOnInit(): void {
    this.getPlaylistsDetails();
  }

  getPlaylistsDetails() {
    this.musicLibraryService.getPlaylistDetails().subscribe(res => this.playlistDetails = res)
  }
}
