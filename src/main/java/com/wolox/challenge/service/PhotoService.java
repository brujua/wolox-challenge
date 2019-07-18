package com.wolox.challenge.service;

import com.wolox.challenge.entity.Photo;

import java.util.List;

public interface PhotoService {

    Photo findById(Long id);

    List<Photo> findByAlbumId(Long albumId);

    List<Photo> findAll();

    List<Photo> findByUserId(Long userId);
}
