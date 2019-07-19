package com.wolox.challenge.service;

import com.wolox.challenge.entity.AlbumPermission;
import com.wolox.challenge.entity.Permission;
import com.wolox.challenge.repository.AlbumPermissionsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumPermissionServiceImpl implements AlbumPermissionService{

    private final AlbumPermissionsRepository albumPermissionsRepository;


    public AlbumPermissionServiceImpl(AlbumPermissionsRepository albumPermissionsRepository) {
        this.albumPermissionsRepository = albumPermissionsRepository;
    }

    @Override
    public Optional<AlbumPermission> searchByAlbumIdAndUserId(Long albumId, Long userId) {
        return albumPermissionsRepository.findByAlbumIdAndUserId(albumId,userId);
    }

    @Override
    public List<AlbumPermission> searchByAlbumIdAndPermission(Long albumId, Permission permission) {
        return albumPermissionsRepository.findByAlbumIdAndPermission(albumId, permission);
    }

    @Transactional
    @Override
    public AlbumPermission insert(AlbumPermission albumPermission) {
        return albumPermissionsRepository.save(albumPermission);
    }

    @Transactional
    @Override
    public void update(AlbumPermission albumPermission) {
        albumPermissionsRepository.save(albumPermission);
    }

    @Transactional
    @Override
    public void remove(AlbumPermission albumPermission) {
        albumPermissionsRepository.delete(albumPermission);
    }


}
