import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Song } from '../models/song';
import { AlbumDetails } from '../models/album-details';
import { playlistDetails } from '../models/playlist-details';

@Injectable({
  providedIn: 'root'
})
export class MusicLibraryService {
  private apiUrl = "http://localhost:8080/api/music-library";

  constructor(private http: HttpClient) { }

  getRecent(): Observable<Song[]> {
    return this.http.get<Song[]>(`${this.apiUrl}/song/recent`);
  }

  getGenres(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/song/genre`);
  }

  getAlbumsDetails(): Observable<AlbumDetails[]> {
    return this.http.get<AlbumDetails[]>(`${this.apiUrl}/album/details`);
  }

  getPlaylistDetails(): Observable<playlistDetails[]> {
    return this.http.get<playlistDetails[]>(`${this.apiUrl}/playlist/details`);
  }
}
