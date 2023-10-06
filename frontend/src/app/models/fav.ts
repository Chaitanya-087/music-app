import { Song } from "./song";

export interface Fav {
    id: number;
    name: string;
    type: string;
    songs: Song[];
}