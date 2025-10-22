package com.sam.spring.graphql.service.impl;

import com.sam.spring.graphql.entity.Post;
import com.sam.spring.graphql.entity.User;
import com.sam.spring.graphql.repository.PostRepository;
import com.sam.spring.graphql.repository.UserRepository;
import com.sam.spring.graphql.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(String name, String email) {
        User user = User.builder()
                .name(name)
                .email(email)
                .build();
        return userRepository.save(user);
    }

    @Override
    public Map<User, List<Post>> fetchUserWithBatchMapping(List<User> users) {
        List<Integer> userIdList = users.stream()
                .map(User::getId)
                .toList();

        List<Post> posts = postRepository
                .findAllByUserIdIn(userIdList);
        System.out.println("Posts " + posts);
        Map<Integer, List<Post>> listMap = posts.stream()
                .collect(Collectors.groupingBy(Post::getId));

        System.out.println("Group Post By User " + listMap);

        Map<User, List<Post>> userListMap = users.stream()
                .collect(Collectors.toMap(Function.identity(), user -> listMap.getOrDefault(user.getId(), new ArrayList<>())));
        System.out.println("Final User Map" + userListMap);
        return userListMap;
    }

    @Override
    public List<List<Post>> fetchUserWithBatchMap(List<User> users) {
        List<Integer> userIdList = users.stream()
                .map(User::getId)
                .toList();

        List<Post> posts = postRepository
                .findAllByUserIdIn(userIdList);
        System.out.println("Posts " + posts);
        Map<Integer, List<Post>> listMap = posts.stream()
                .collect(Collectors.groupingBy(Post::getId));
        List<List<Post>> lists = users.stream()
                .map(u -> listMap.getOrDefault(u.getId(), new ArrayList<>()))
                .toList();

        return lists;
    }

    public CompletableFuture<Map<User, List<Post>>> fetchUserBatchMapAsync(List<User> users) {
        return CompletableFuture.supplyAsync(() -> {
            List<Integer> userIdList = users.stream()
                    .map(User::getId)
                    .toList();

            List<Post> posts = postRepository
                    .findAllByUserIdIn(userIdList);
            Map<Integer, List<Post>> listMap = posts.stream()
                    .collect(Collectors.groupingBy(Post::getId));
            Map<User, List<Post>> userListMap = users.stream()
                    .collect(Collectors.toMap(Function.identity(), user -> listMap.getOrDefault(user.getId(), new ArrayList<>())));
            return userListMap;
        });
    }
}
