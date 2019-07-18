package com.wolox.challenge.service.external;

import com.wolox.challenge.entity.User;
import com.wolox.challenge.service.UserService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ExternalUserService implements UserService {

    private final RestTemplate restTemplate;

    public ExternalUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public User findById(Long id) {
        return restTemplate.getForObject("http://jsonplaceholder.typicode.com/users/"+id, User.class);
    }

    @Override
    public List<User> findAll() {
        // Need to use a ParameterizedTypeReference given that the api returns a list and not a valid json object,
        // which can be considered bad practice.
        ResponseEntity<List<User>> response = restTemplate.exchange(
                "http://jsonplaceholder.typicode.com/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>(){});
        return response.getBody();
    }
}
