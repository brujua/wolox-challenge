package com.wolox.challenge.web;

import com.wolox.challenge.entity.Photo;
import com.wolox.challenge.service.PhotoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/photos")
public class PhotosController {

    private final PhotoService photoService;

    public PhotosController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @RequestMapping("")
    public List<Photo> photos(@RequestParam(required = false, name = "userId") Long userId){
        if(userId == null)
            return photoService.findAll();
        return photoService.findByUserId(userId);
    }

    @RequestMapping("/{id}")
    public Photo photo(@PathVariable Long id){
        return photoService.findById(id);
    }
}
