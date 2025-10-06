package com.sam.spring.graphql.service;

import com.sam.spring.graphql.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    List<User> findByEmail(String email);
    User createUser(String name, String email);
}