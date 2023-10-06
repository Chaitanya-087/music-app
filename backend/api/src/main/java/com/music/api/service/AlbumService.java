package com.music.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.api.dto.AlbumDetailsDTO;
import com.music.api.entity.Album;
import com.music.api.entity.Artist;
import com.music.api.entity.Role;
import com.music.api.repository.AlbumRepository;
import com.music.api.repository.ArtistRepository;
import com.music.api.repository.RoleRepository;

import jakarta.transaction.Transactional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Transactional
    public Album addAlbum(Album album) {
        List<Artist> artists = album.getArtists();
        Album newAlbum = new Album();
        newAlbum.setTitle(album.getTitle());
        for (Artist artist : artists) {
            Artist existingArtist = artistRepository.findByUsername(artist.getUsername()).orElseGet(() -> {
                Artist newArtist = new Artist();
                newArtist.setUsername(artist.getUsername());
                newArtist.setCountry(artist.getCountry());
                for (Role role : artist.getRoles()) {
                    Role existingRole = roleRepository.findByName(role.getName()).orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setName(role.getName());
                        return roleRepository.save(newRole);
                    });
                    newArtist.getRoles().add(existingRole);
                }
                return artistRepository.save(newArtist);
            });
            newAlbum.getArtists().add(existingArtist);
        }
        return albumRepository.save(newAlbum);
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public List<AlbumDetailsDTO> getAlbumsDetails() {
        return albumRepository.findAll().stream().map(album -> createAlbumDetailsDTO(album))
                .collect(java.util.stream.Collectors.toList());
    }

    

    private AlbumDetailsDTO createAlbumDetailsDTO(Album album) {
        AlbumDetailsDTO albumDetailsDTO = new AlbumDetailsDTO();
        albumDetailsDTO.setId(album.getId());
        albumDetailsDTO.setTitle(album.getTitle());
        albumDetailsDTO.setLeadArtist(album.getArtists().get(0).getUsername());
        return albumDetailsDTO;
    }
    // public
}
