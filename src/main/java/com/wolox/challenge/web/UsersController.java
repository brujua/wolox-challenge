package com.wolox.challenge.web;

import com.wolox.challenge.entity.Photo;
import com.wolox.challenge.entity.User;
import com.wolox.challenge.service.PhotoService;
import com.wolox.challenge.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final PhotoService photoService;


    public UsersController(UserService userService, PhotoService photoService) {
        this.userService = userService;
        this.photoService = photoService;
    }

    @RequestMapping("")
    public List<User> users(){
        return userService.findAll();
    }

    @RequestMapping("/{id}")
    public User user(@PathVariable("id") Long id){
        return userService.findById(id);
    }

    @RequestMapping("/{id}/photos")
    public List<Photo> userPhotos(@PathVariable("id") Long id){
        return photoService.findByUserId(id);
    }



}
