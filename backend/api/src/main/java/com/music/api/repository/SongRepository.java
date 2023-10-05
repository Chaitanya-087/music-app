package com.music.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.music.api.entity.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
    
}
