package com.sam.spring.graphql.service;

import com.sam.spring.graphql.entity.Post;

public interface PostService {
    Post createPost(Integer userId, String title, String content);
}