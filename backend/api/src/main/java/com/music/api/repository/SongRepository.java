package com.music.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.music.api.entity.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    
    
}

