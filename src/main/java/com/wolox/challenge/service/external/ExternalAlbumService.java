package com.wolox.challenge.service.external;

import com.wolox.challenge.entity.Album;
import com.wolox.challenge.service.AlbumService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ExternalAlbumService implements AlbumService {

    private static final String ERR_MSG_NO_ALBUM = "No album with such Id";
    private RestTemplate restTemplate;

    public ExternalAlbumService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Album findById(Long id) {
        try{
            return restTemplate.getForObject("/albums/"+id, Album.class);
        }catch (HttpClientErrorException e){
            if(e.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, ERR_MSG_NO_ALBUM);
            }
            throw e;
        }
    }

    @Override
    public List<Album> findByUserId(Long userId) {
        String url = "/albums?userId=" + userId;
        ResponseEntity<List<Album>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Album>>(){});
        return response.getBody();
    }

    @Override
    public List<Album> findAll() {
        ResponseEntity<List<Album>> response = restTemplate.exchange(
                "/albums",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Album>>(){});
        return response.getBody();
    }
}
