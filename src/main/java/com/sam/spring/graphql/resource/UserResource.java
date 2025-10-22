package com.sam.spring.graphql.resource;

import com.sam.spring.graphql.entity.Post;
import com.sam.spring.graphql.entity.User;
import com.sam.spring.graphql.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;

    @QueryMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @QueryMapping
    public List<User> findUsersByEmail(@Argument String email) {
        return userService.findByEmail(email);
    }

    @MutationMapping
    public User createUser(@Argument String name, @Argument String email) {
        return userService.createUser(name, email);
    }


    /*@SchemaMapping // Use same thread, needs to wait more time. to resolve this can be enabled virtual thread
    public List<Post> posts(User user) throws InterruptedException {
        log.info("Retrieve Post by passing user {}", user.getName());
        Thread.sleep(1000);
        return new ArrayList<>();
    }*/

    /*@BatchMapping
    public Map<User, List<Post>> posts(List<User> users) {
        System.out.println("Calling");
        return userService.fetchUserWithBatchMapping(users);
    }*/


   /* @BatchMapping
    public List<List<Post>> posts(List<User> users) {
        System.out.println("Calling");
        return userService.fetchUserWithBatchMap(users);
    }*/

    @BatchMapping
    public CompletableFuture<Map<User, List<Post>>> posts(List<User> users) {
        return userService.fetchUserBatchMapAsync(users);
    }

}
