import { Component } from '@angular/core';
import { MusicLibraryService } from '../services/music-library.service';
import { Song } from '../models/song';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css']
})
export class PlaylistComponent {
  id!: number;
  songs: Song[] = [];
  constructor(private musicLibraryService: MusicLibraryService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      console.log(params.get('id'));
      this.id = Number(params.get('id'));
      this.getPlaylistDetails();
    });
  }

  getPlaylistDetails() {
    this.musicLibraryService.getPlaylistById(this.id).subscribe(res => this.songs = res);
  }
}
