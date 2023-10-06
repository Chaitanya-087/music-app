package com.music.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class GenreSongsDTO {
    String title;
    List<SongDTO> songs;
}
