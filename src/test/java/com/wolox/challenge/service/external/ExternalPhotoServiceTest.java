package com.wolox.challenge.service.external;

import com.wolox.challenge.entity.Photo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ExternalPhotoServiceTest {

    @Autowired
    ExternalPhotoService serv;

    @Test
    public void findById() {
        Photo photo = serv.findById(1L);
        assertThat(photo.getId()).isEqualTo(1L);
    }

    @Test
    public void findByAlbumId() {
        List<Photo> photos = serv.findByAlbumId(1L);
        assertThat(photos.size()).isGreaterThan(0);
        photos.forEach(photo -> assertThat(photo.getAlbumId()).isEqualTo(1L));
    }

    @Test
    public void findAll() {
        List<Photo> photos = serv.findAll();
        assertThat(photos.size()).isGreaterThan(0);
    }
}