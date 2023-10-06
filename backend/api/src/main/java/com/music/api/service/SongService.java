package com.music.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.text.html.Option;

import org.hibernate.cache.spi.access.CollectionDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.music.api.binding.SongBody;
import com.music.api.dto.CollectionDetailsDTO;
import com.music.api.dto.SongDTO;
import com.music.api.entity.Album;
import com.music.api.entity.Artist;
import com.music.api.entity.Collection;
import com.music.api.entity.FavouriteSongs;
import com.music.api.entity.Song;
import com.music.api.entity.User;
import com.music.api.repository.AlbumRepository;
import com.music.api.repository.CollectionRepository;
import com.music.api.repository.FavouriteSongsRepository;
import com.music.api.repository.SongRepository;
import com.music.api.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private FavouriteSongsRepository favouriteSongsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private AlbumRepository albumRepository;

    public Song addSong(SongBody song) {
        Song newSong = new Song();
        newSong.setTitle(song.getTitle());
        newSong.setGenre(song.getGenre());
        newSong.setDuration(song.getDuration());
        Album album = albumRepository.findByTitle(song.getAlbum()).orElse(null);
        newSong.setAlbum(album);
        List<Artist> artists = song.getArtists();
        for (Artist artist : artists) {
            newSong.getArtists().add(artist);
        }
        return songRepository.save(newSong);
        // return songRepository.save(song);
    }

    public List<SongDTO> getAllSongs() {
        return songRepository.findAll().stream().map(song -> createSongDTO(song)).collect(Collectors.toList());
    }

    public List<SongDTO> getSongsByAlbum(Album album) {
        return songRepository.findByAlbum(album).stream().map(song -> createSongDTO(song)).collect(Collectors.toList());
    }

    @Transactional
    public String favouriteSong(Long songId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).get();
        FavouriteSongs favouriteSongs = favouriteSongsRepository.findByUser(user).orElseGet(() -> {
            FavouriteSongs newFavouriteSongs = new FavouriteSongs();
            newFavouriteSongs.setCollectionName("your favourites");
            newFavouriteSongs.setUser(user);
            return favouriteSongsRepository.save(newFavouriteSongs);
        });
        Optional<Song> optionalSong = songRepository.findById(songId);
        if (optionalSong.isEmpty())
            return "Song not found";
        Song song = optionalSong.get();
        if (favouriteSongs.getSongs().contains(song)) {
            favouriteSongs.getSongs().remove(song);
            favouriteSongsRepository.save(favouriteSongs);
            return "Song removed from favourites";
        } else {
            favouriteSongs.getSongs().add(song);
            favouriteSongsRepository.save(favouriteSongs);
            return "Song added to favourites";
        }
    }

    public FavouriteSongs getFavouriteSongs() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).get();

        return favouriteSongsRepository.findByUser(user).orElseGet(() -> {
            FavouriteSongs newFavouriteSongs = new FavouriteSongs();
            newFavouriteSongs.setCollectionName("your favourites");
            newFavouriteSongs.setUser(user);
            return favouriteSongsRepository.save(newFavouriteSongs);
        });
    }

    public SongDTO createSongDTO(Song song) {
        SongDTO songDTO = new SongDTO();
        songDTO.setId(song.getId());
        songDTO.setTitle(song.getTitle());
        songDTO.setGenre(song.getGenre());
        songDTO.setDuration(song.getDuration());
        songDTO.setAlbumTitle(song.getAlbum() == null ? "Single" : song.getAlbum().getTitle());
        songDTO.setArtists(song.getArtists());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).get();
        favouriteSongsRepository.findByUser(user).ifPresent(favouriteSongs -> {
            songDTO.setFavourited(favouriteSongs.getSongs().contains(song));
        });
        long count = favouriteSongsRepository.findAll().stream()
                .filter(favouriteSongs -> favouriteSongs.getSongs().contains(song))
                .collect(Collectors.toList())
                .size();
        songDTO.setFavouritesCount(count);
        return songDTO;
    }

    public List<SongDTO> getRecentSongs() {
        return songRepository.findTop10ByOrderByIdDesc().stream().map(song -> createSongDTO(song))
                .collect(Collectors.toList());
    }

    public List<String> getGenres() {
        return songRepository.findGenres();
    }

    public List<SongDTO> getSongsByGenre(String genre) {
        return songRepository.findByGenre(genre).stream().map(song -> createSongDTO(song))
                .collect(Collectors.toList());
    }

    public List<SongDTO> getSongsByAlbumTitle(String albumTitle) {
        return songRepository.findByAlbumTitle(albumTitle).stream().map(song -> createSongDTO(song))
                .collect(Collectors.toList());
    }

    public List<Collection> getUserCollections() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).get();
        return collectionRepository.findByUser(user);
    }

    

    public CollectionDetailsDTO getFavouriteCollectionDetailsDTO() {
        CollectionDetailsDTO collectionDetailsDTO = new CollectionDetailsDTO();
        FavouriteSongs favouriteSongs = getFavouriteSongs();
        collectionDetailsDTO.setId(favouriteSongs.getId());
        collectionDetailsDTO.setName(favouriteSongs.getCollectionName());
        collectionDetailsDTO.setType("your likings");
        collectionDetailsDTO.setCount(favouriteSongs.getSongs().size());
        return collectionDetailsDTO;

    }
}
