package com.wolox.challenge.web;

import com.wolox.challenge.entity.Album;
import com.wolox.challenge.service.AlbumService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {

    private final AlbumService albumService;

    public AlbumsController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @RequestMapping("")
    public List<Album> albums(@RequestParam(required = false) Long userId){
        if( userId == null)
            return albumService.findAll();
        return albumService.findByUserId(userId);
    }

    @RequestMapping("/{id}")
    public Album album(@PathVariable Long id){
        return albumService.findById(id);
    }

}
