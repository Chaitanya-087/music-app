package com.music.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.music.api.entity.FavouriteSongs;
import com.music.api.entity.User;

@Repository
public interface FavouriteSongsRepository extends JpaRepository<FavouriteSongs, Long> {
    Optional<FavouriteSongs> findByUser(User user);
}
