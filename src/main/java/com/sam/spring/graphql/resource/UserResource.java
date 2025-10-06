package com.sam.spring.graphql.resource;

import com.sam.spring.graphql.entity.User;
import com.sam.spring.graphql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

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
}
