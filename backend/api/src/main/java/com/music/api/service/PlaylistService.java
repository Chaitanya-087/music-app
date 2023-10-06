package com.music.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.music.api.dto.CollectionDetailsDTO;
import com.music.api.dto.SongDTO;
import com.music.api.entity.Playlist;
import com.music.api.entity.Song;
import com.music.api.entity.User;
import com.music.api.repository.PlaylistRepository;
import com.music.api.repository.SongRepository;
import com.music.api.repository.UserRepository;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongService songService;

    public Playlist createPlaylist(String playlistName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).get();
        Playlist playlist = new Playlist();
        playlist.setCollectionName(playlistName);
        playlist.setUser(user);
        return playlistRepository.save(playlist);
    }

    public String addToPlaylist(Long playlistId, Long songId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).get();
        Playlist playlist = playlistRepository.findByUserIdAndId(user.getId(),playlistId).get();
        Song song = songRepository.findById(songId).get();
        playlist.getSongs().add(song);
        playlistRepository.save(playlist);
        return "Song added to playlist";
    }

    public List<Playlist> getUserPlaylists() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).get();
        return playlistRepository.findByUserId(user.getId());
    }

    public List<CollectionDetailsDTO> getPlaylistDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).get();
        return playlistRepository.findByUserId(user.getId()).stream().map(playlist -> createCollectionDetailsDTO(playlist))
                .collect(java.util.stream.Collectors.toList());
    }

    public CollectionDetailsDTO createCollectionDetailsDTO(Playlist playlist) {
        CollectionDetailsDTO collectionDetailsDTO = new CollectionDetailsDTO();
        collectionDetailsDTO.setId(playlist.getId());
        collectionDetailsDTO.setName(playlist.getCollectionName());
        collectionDetailsDTO.setCount(playlist.getSongs().size());
        return collectionDetailsDTO;
    }

    public List<SongDTO> getPlaylistSongs(Long playlistId) {
        return playlistRepository.findById(playlistId).get().getSongs().stream().map(song -> songService.createSongDTO(song))
                .collect(java.util.stream.Collectors.toList());
    }

    // //TODO:
    // public List<SongDTO> getPlaylistSongs(Long playlistId) {
    //     return playlistRepository.findById(playlistId).get().getSongs();
    // }
}
