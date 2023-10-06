import { Song } from "./song";

export interface Playlist {
    id: number;
    name: string;
    type: string;
    songs: Song[];
}