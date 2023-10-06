package com.music.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.music.api.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    @Query(value = "SELECT * FROM roles WHERE name = ?1 LIMIT 1", nativeQuery = true)
    Optional<Role> findByName(String name);
}