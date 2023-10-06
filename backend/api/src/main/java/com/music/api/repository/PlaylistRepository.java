package com.music.api.repository;

import org.springframework.stereotype.Repository;

import com.music.api.entity.Playlist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;



@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Optional<Playlist> findByUserIdAndId(Long userId, Long id);

    List<Playlist> findByUserId(Long userId);
}

