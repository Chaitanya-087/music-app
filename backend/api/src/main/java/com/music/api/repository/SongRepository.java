package com.music.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.music.api.entity.Album;
import com.music.api.entity.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
    
    List<Song> findByAlbum(Album album);

    List<Song> findTop10ByOrderByIdDesc();

    @Query(value = "SELECT DISTINCT genre FROM songs", nativeQuery = true)
    List<String> findGenres();


    List<Song> findByGenre(String genre);

    @Query(value = "SELECT DISTINCT album_title FROM songs WHERE genre = ?1", nativeQuery = true)
    List<String> findAlbumTitles();

    List<Song> findByAlbumTitle(String albumTitle);
}
