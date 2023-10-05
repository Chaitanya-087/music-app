package com.music.api.repository;

import org.springframework.stereotype.Repository;

import com.music.api.entity.Playlist;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    
}

