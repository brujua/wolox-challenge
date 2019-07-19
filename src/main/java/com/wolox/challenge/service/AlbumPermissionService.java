package com.wolox.challenge.service;

import com.wolox.challenge.entity.AlbumPermission;
import com.wolox.challenge.entity.Permission;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface AlbumPermissionService {

    Optional<AlbumPermission> searchByAlbumIdAndUserId(Long albumId, Long userId);

    List<AlbumPermission> searchByAlbumIdAndPermission(Long albumId, Permission permission);

    @Transactional
    AlbumPermission insert(AlbumPermission albumPermission);

    @Transactional
    void update(AlbumPermission albumPermission);

    @Transactional
    void remove(AlbumPermission albumPermission);
}
