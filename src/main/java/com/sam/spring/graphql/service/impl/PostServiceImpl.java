package com.sam.spring.graphql.service.impl;

import com.sam.spring.graphql.entity.Post;
import com.sam.spring.graphql.entity.User;
import com.sam.spring.graphql.repository.PostRepository;
import com.sam.spring.graphql.repository.UserRepository;
import com.sam.spring.graphql.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public Post createPost(Integer userId, String title, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        Post post = Post.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
        return postRepository.save(post);
    }
}
