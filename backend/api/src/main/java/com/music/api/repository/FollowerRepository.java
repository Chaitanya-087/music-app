package com.music.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.music.api.entity.Follower;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {

}
