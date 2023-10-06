package com.music.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.music.api.binding.MessageResponse;
import com.music.api.binding.SongBody;
import com.music.api.dto.AlbumDTO;
import com.music.api.dto.AlbumDetailsDTO;
import com.music.api.dto.CollectionDetailsDTO;
import com.music.api.dto.SongDTO;
import com.music.api.entity.Album;
import com.music.api.entity.Collection;
import com.music.api.entity.FavouriteSongs;
import com.music.api.entity.Playlist;
import com.music.api.entity.Song;
import com.music.api.repository.AlbumRepository;
import com.music.api.service.AlbumService;
import com.music.api.service.PlaylistService;
import com.music.api.service.SongService;

@RestController
@RequestMapping("/api/music-library")
public class MusicLibraryController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private AlbumRepository albumRepository;

    @PostMapping("/song")
    public Song addSong(@RequestBody SongBody song) {
        return songService.addSong(song);
    }

    @GetMapping("/song/all")
    public List<SongDTO> getAllSongs() {
        return songService.getAllSongs();
    }

    @PostMapping("/album")
    // @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public Album addAlbum(@RequestBody Album album) {
        System.out.println("album: " + album);
        return albumService.addAlbum(album);
    }

    @PostMapping("/song/favourite")
    public ResponseEntity<MessageResponse> addFavouriteSong(@RequestBody Map<String, Long> songBody) {
        String message = songService.favouriteSong(songBody.get("id"));
        return ResponseEntity.ok(new MessageResponse(message));
    }

    @GetMapping("/song/favourite/user")
    public List<SongDTO> getFavouriteSongs() {

        return songService.getFavouriteSongs().getSongs().stream().map(song -> songService.createSongDTO(song))
                .collect(java.util.stream.Collectors.toList());
    }

    @PostMapping("/playlist")
    public Playlist createPlaylist(@RequestBody Map<String, String> playlistBody) {
        return playlistService.createPlaylist(playlistBody.get("name"));
    }

    @PostMapping("/playlist/song")
    public ResponseEntity<MessageResponse> addToPlaylist(@RequestBody Map<String, Long> playlistBody) {
        playlistService.addToPlaylist(playlistBody.get("playlistId"), playlistBody.get("songId"));
        return ResponseEntity.ok().body(new MessageResponse("created successfully"));
    }

    @GetMapping("/playlist/user")
    public List<Playlist> getPlaylists() {
        return playlistService.getUserPlaylists();

    }

    @GetMapping("/playlist/{playlistId}")
    public List<SongDTO> getSongsByPlaylist(@PathVariable("playlistId") Long playlistId) {
        return playlistService.getPlaylistSongs(playlistId);
    }

    // @GetMapping("/album/{id}")
    // public List<SongDTO> getSongsByAlbum(@PathVariable("id") Long id) {
    //     Album album = albumRepository.findById(id).get();
    //     return songService.getSongsByAlbum(album);
    // }

    @GetMapping("/playlist/details")
    public List<CollectionDetailsDTO> getPlaylistDetails() {
        List<CollectionDetailsDTO> playlistDetails = playlistService.getPlaylistDetails();
        playlistDetails.add(songService.getFavouriteCollectionDetailsDTO());
        return playlistDetails;
    }

    @GetMapping("/song/recent")
    public List<SongDTO> getRecentSongs() {
        return songService.getRecentSongs();
    }

    @GetMapping("/song/genre")
    public List<String> getGenres() {
        return songService.getGenres();
    }

    @GetMapping("/song/genre/{genre}")
    public List<SongDTO> getSongsByGenre(@PathVariable("genre") String genre) {
        return songService.getSongsByGenre(genre);
    }

    @GetMapping("/album/details")
    public List<AlbumDetailsDTO> getAllAlbumDetails() {
        return albumService.getAlbumsDetails();
    }

    @GetMapping("/album/{albumTitle}")
    public List<SongDTO> getSongsByAlbum(@PathVariable("albumTitle") String albumTitle) {
        return songService.getSongsByAlbumTitle(albumTitle);
    }

    // @GetMapping("/collection/user")
    // public List<Collection> getCollections() {
    // return songService.getUserCollections();
    // }
}
