package com.music.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.api.repository.AlbumRepository;

@Service
public class AlbumService {
    
    @Autowired
    private AlbumRepository albumRepository;

    // public 
}
