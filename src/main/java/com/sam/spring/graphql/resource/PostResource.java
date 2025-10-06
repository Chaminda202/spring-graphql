package com.sam.spring.graphql.resource;

import com.sam.spring.graphql.entity.Post;
import com.sam.spring.graphql.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PostResource {
    private final PostService postService;

    @MutationMapping
    public Post createPost(@Argument Integer userId, @Argument String title, @Argument String content) {
        return postService.createPost(userId, title, content);
    }
}
