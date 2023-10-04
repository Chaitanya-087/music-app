package com.music.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseUser {
    
    private String password;
    private String role;
}
