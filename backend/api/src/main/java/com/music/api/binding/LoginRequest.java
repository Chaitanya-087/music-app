package com.music.api.binding;

import lombok.Data;

@Data
public class LoginRequest {
    
    private String username;
    private String password;
}
