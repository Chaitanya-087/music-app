package com.music.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.music.api.entity.FollowerId;

@Repository
public interface FollowerIdRepository extends JpaRepository<FollowerId, Long> {

}
