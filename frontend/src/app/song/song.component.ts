import { Component, Input } from '@angular/core';
import { Song } from '../models/song';

@Component({
  selector: 'app-song',
  templateUrl: './song.component.html',
  styleUrls: ['./song.component.css']
})
export class SongComponent {
  @Input() song!: Song;
}
