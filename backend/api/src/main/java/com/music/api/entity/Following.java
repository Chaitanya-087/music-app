package com.music.api.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "followings")
public class Following {
    
    @EmbeddedId
    private FollowingId id;
}
