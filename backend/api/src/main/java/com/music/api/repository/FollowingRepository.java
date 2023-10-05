package com.music.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.music.api.entity.Following;

@Repository
public interface FollowingRepository extends JpaRepository<Following, Long> {

}
