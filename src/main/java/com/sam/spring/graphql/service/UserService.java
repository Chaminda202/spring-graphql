package com.sam.spring.graphql.service;

import com.sam.spring.graphql.entity.Post;
import com.sam.spring.graphql.entity.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface UserService {
    List<User> getUsers();
    List<User> findByEmail(String email);
    User createUser(String name, String email);
    Map<User, List<Post>> fetchUserWithBatchMapping(List<User> users);
    List<List<Post>> fetchUserWithBatchMap(List<User> users);
    CompletableFuture<Map<User, List<Post>>> fetchUserBatchMapAsync(List<User> users);
}