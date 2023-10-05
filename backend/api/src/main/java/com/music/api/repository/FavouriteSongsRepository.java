package com.music.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.music.api.entity.FavouriteSongs;

@Repository
public interface FavouriteSongsRepository extends JpaRepository<FavouriteSongs, Long> {

}
