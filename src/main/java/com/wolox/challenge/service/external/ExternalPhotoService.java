package com.wolox.challenge.service.external;

import com.wolox.challenge.entity.Album;
import com.wolox.challenge.entity.Photo;
import com.wolox.challenge.service.AlbumService;
import com.wolox.challenge.service.PhotoService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExternalPhotoService implements PhotoService {

    private static final String ERR_MSG_NO_PHOTO = "No photo with that Id";

    private final RestTemplate restTemplate;

    private final AlbumService albumService;

    public ExternalPhotoService(RestTemplate restTemplate, AlbumService albumService) {
        this.restTemplate = restTemplate;
        this.albumService = albumService;
    }

    @Override
    public Photo findById(Long id) {
        try{
            return restTemplate.getForObject("/photos/"+id, Photo.class);
        } catch (HttpClientErrorException e){
            if(e.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, ERR_MSG_NO_PHOTO);
            }
            throw e;
        }
    }

    @Override
    public List<Photo> findByAlbumId(Long albumId) {
        String url = "/photos?albumId=" + albumId;
        ResponseEntity<List<Photo>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Photo>>(){});
        return response.getBody();
    }

    @Override
    public List<Photo> findAll() {
        ResponseEntity<List<Photo>> response = restTemplate.exchange(
                "/photos",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Photo>>(){});
        return response.getBody();
    }

    @Override
    public List<Photo> findByUserId(Long userId) {
        List<Album> userAlbums = albumService.findByUserId(userId);
        List<Photo> userPhotos = new ArrayList<>();
        userAlbums.forEach(album -> {
            userPhotos.addAll(findByAlbumId(album.getId()));
        });
        return userPhotos;
    }
}
