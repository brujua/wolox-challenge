package com.wolox.challenge.web;

import com.wolox.challenge.entity.Photo;
import com.wolox.challenge.service.PhotoService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photos")
public class PhotosController {

    private final PhotoService photoService;

    public PhotosController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("")
    public List<Photo> photos( @ApiParam(value = "User-Id") @RequestParam(required = false, name = "userId") Long userId){
        if(userId == null)
            return photoService.findAll();
        return photoService.findByUserId(userId);
    }

    @GetMapping("/{id}")
    public Photo photo(@PathVariable Long id){
        return photoService.findById(id);
    }
}
