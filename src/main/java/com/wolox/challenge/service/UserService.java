package com.wolox.challenge.service;

import com.wolox.challenge.entity.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    List<User> findAll();
}
