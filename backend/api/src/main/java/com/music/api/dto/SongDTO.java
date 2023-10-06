package com.music.api.dto;

import java.util.HashSet;
import java.util.Set;

import com.music.api.entity.Artist;

import lombok.Data;

@Data
public class SongDTO {
    private Long id;
    private String title;
    private String genre;
    private String duration;
    private String albumTitle;
    private boolean isFavourited;
    private long favouritesCount;
    private Set<Artist> artists = new HashSet<>();
}
