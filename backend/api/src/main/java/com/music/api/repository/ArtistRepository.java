package com.music.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.music.api.entity.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByUsername(String username);
}
