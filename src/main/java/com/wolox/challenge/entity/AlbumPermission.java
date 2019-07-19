package com.wolox.challenge.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "album_permissions")
public class AlbumPermission {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "album_id")
    private Long albumId;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "permission")
    private Permission permission;

    public AlbumPermission() {
    }

    public AlbumPermission(Long id, Long albumId, Long userId, Permission permission) {
        this.id = id;
        this.albumId = albumId;
        this.userId = userId;
        this.permission = permission;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
