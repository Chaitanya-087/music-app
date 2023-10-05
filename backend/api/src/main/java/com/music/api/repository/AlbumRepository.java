package com.music.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.music.api.entity.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    
}
