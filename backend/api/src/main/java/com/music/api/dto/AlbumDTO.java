package com.music.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.music.api.entity.Artist;

import lombok.Data;

@Data
public class AlbumDTO {
    private Long id;
    private String title;
    private List<Artist> artists = new ArrayList<>();
    private List<SongDTO> songs = new ArrayList<>();
}
