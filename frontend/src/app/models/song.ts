import { Artist } from "./artist";

export interface Song {
    id : number,
    title: string,
    genre: string,
    duration: string,
    albumTitle?: string,
    favoritesCount: number,
    artists: Artist[]
}
