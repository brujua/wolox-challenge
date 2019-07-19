package com.wolox.challenge.web;

import com.wolox.challenge.entity.Album;
import com.wolox.challenge.service.AlbumService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {

    private final AlbumService albumService;

    public AlbumsController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("")
    public List<Album> albums(@ApiParam(value = "User-Id") @RequestParam(required = false) Long userId){
        if( userId == null)
            return albumService.findAll();
        return albumService.findByUserId(userId);
    }

    @GetMapping("/{id}")
    public Album album(@PathVariable Long id){
        return albumService.findById(id);
    }

}
