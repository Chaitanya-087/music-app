import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Song } from '../models/song';
import { MusicLibraryService } from '../services/music-library.service';

@Component({
  selector: 'app-genre',
  templateUrl: './genre.component.html',
  styleUrls: ['./genre.component.css']
})
export class GenreComponent {
  name!: string;
  songs: Song[] = [];
  constructor(private route: ActivatedRoute,private musicLibraryService: MusicLibraryService) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.name = params.get('name') as string;
      this.getSongsByGenre();
    });
  }

  getSongsByGenre() {
    this.musicLibraryService.getSongsByGenre(this.name).subscribe(res => this.songs = res);
  }

}
