package com.music.api.dto;

import lombok.Data;

@Data
public class CollectionDetailsDTO {
    private Long id;
    private String name;
    private String type = "playlist";
    private int count;
}
