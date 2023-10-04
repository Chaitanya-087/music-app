package com.music.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/music")
public class MusicLibraryController {

    @GetMapping
    public String getMusic() {
        return "Music Library";
    }
}
