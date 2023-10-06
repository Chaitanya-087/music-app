package com.music.api.binding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.music.api.entity.Artist;

import lombok.Data;

@Data
public class SongBody {
    private String title;
    private String genre;
    private String duration;
    private String album;

    private List<Artist> artists = new ArrayList<>();
}
