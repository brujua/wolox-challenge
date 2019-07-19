package com.wolox.challenge.repository;

import com.wolox.challenge.entity.AlbumPermission;
import com.wolox.challenge.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlbumPermissionsRepository extends JpaRepository<AlbumPermission, Long> {

    Optional<AlbumPermission> findById(Long id);

    Optional<AlbumPermission> findByAlbumIdAndUserId(Long albumId, Long userId);

    List<AlbumPermission> findByAlbumIdAndPermission(Long albumId, Permission permission);

}
