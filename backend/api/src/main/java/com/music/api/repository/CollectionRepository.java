package com.music.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.music.api.entity.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

}