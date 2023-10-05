package com.music.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.music.api.entity.FollowingId;

@Repository
public interface FollowingIdRepository extends JpaRepository<FollowingId, Long> {

}
