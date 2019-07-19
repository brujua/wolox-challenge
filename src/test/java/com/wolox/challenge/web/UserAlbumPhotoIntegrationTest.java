package com.wolox.challenge.web;

import com.wolox.challenge.entity.Album;
import com.wolox.challenge.entity.Photo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserAlbumPhotoIntegrationTest {


    @Value("${server.base.url}")
    private String baseUrl;

    private static final TestRestTemplate testRestTemplate = new TestRestTemplate();


    @Test
    public void userAllEndpoint(){
        // Just proving that the endpoint its up and running.
        ResponseEntity<String> response = testRestTemplate.getForEntity(baseUrl+"/users", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void userByIdEndpoint(){
        // Just proving that the endpoint its up and running.
        ResponseEntity<String> response = testRestTemplate.getForEntity(baseUrl+"/users/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void photosByUserBelongsToThatUserAlbums(){
        //retrieve user (id=1) photos
        ResponseEntity<List<Photo>> response = testRestTemplate.exchange(
                baseUrl + "/photos?userId=1",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Photo>>() {}
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Photo> userPhotos = response.getBody();
        assertThat(userPhotos).isNotNull();

        // retrieve user albums and collect their ids
        ResponseEntity<List<Album>> albumsResponse = testRestTemplate.exchange(
                baseUrl + "/albums?userId=1",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Album>>() {}
        );
        assertThat(albumsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(albumsResponse.getBody()).isNotNull();
        Set<Long> albumsId = albumsResponse.getBody().stream().map(Album::getId).collect(Collectors.toSet());


        userPhotos.forEach(photo -> {
            assertThat(photo.getAlbumId()).isIn(albumsId);
        });
    }

}
