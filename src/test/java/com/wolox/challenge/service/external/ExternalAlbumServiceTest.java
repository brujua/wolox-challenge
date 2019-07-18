package com.wolox.challenge.service.external;

import com.wolox.challenge.entity.Album;
import com.wolox.challenge.service.AlbumService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ExternalAlbumServiceTest {

    @Autowired
    private AlbumService serv;

    @Test
    public void findById() {
        Album album = serv.findById(1L);
        assertThat(album.getId()).isEqualTo(1L);
    }

    @Test
    public void findByUserId() {
        List<Album> albums = serv.findByUserId(1L);
        assertThat(albums.size()).isGreaterThan(0);
        albums.forEach(album -> assertThat(album.getUserId()).isEqualTo(1L));
    }

    @Test
    public void findAll() {
        List<Album> albums = serv.findAll();
        assertThat(albums.size()).isGreaterThan(0);
    }
}