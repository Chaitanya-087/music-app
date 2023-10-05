package com.music.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.music.api.entity.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    
}