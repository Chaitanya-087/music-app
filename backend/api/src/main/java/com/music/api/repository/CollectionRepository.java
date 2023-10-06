package com.music.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.music.api.entity.Collection;
import com.music.api.entity.User;

import java.util.List;


public interface CollectionRepository extends JpaRepository<Collection, Long> {
    List<Collection> findByUser(User user);
}
