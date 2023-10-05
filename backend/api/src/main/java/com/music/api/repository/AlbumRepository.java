package com.music.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.music.api.entity.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    
}
