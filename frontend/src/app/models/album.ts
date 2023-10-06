import { Artist } from "./artist";
import { Song } from "./song";

export interface Album {
    id:  number;
    name: string;
    artist: Artist[];
    songs: Song[];
}