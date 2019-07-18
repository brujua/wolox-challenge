package com.wolox.challenge.service;

import com.wolox.challenge.entity.Album;

import java.util.List;

public interface AlbumService {

    Album findById(Long id);

    List<Album> findByUserId(Long userId);

    List<Album> findAll();
}
