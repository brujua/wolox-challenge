package com.wolox.challenge.service.external;

import com.wolox.challenge.entity.User;
import com.wolox.challenge.service.UserService;
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
public class ExternalUserService implements UserService {

    private static final String ERR_MSG_NO_USER = "No user with that Id";

    private final RestTemplate restTemplate;

    public ExternalUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public User findById(Long id) {
        try {
            return restTemplate.getForObject("/users/"+id, User.class);
        } catch (HttpClientErrorException e){
            if(e.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, ERR_MSG_NO_USER);
            }
            throw e;
        }
    }

    @Override
    public List<User> findAll() {
        // Need to use a ParameterizedTypeReference given that the api returns a list and not a valid json object,
        // which can be considered bad practice.
        ResponseEntity<List<User>> response = restTemplate.exchange(
                "/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>(){});
        return response.getBody();
    }
}
