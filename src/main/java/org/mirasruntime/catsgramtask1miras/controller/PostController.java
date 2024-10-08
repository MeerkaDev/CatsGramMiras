package org.mirasruntime.catsgramtask1miras.controller;

import org.mirasruntime.catsgramtask1miras.exceptions.NotFoundException;
import org.mirasruntime.catsgramtask1miras.model.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class PostController {
    private List<Post> posts = new ArrayList<>();

    @GetMapping("/posts")
    public List<Post> findAll() {
        return posts;
    }

    @PostMapping(value = "/post")
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @GetMapping("/post/{postId}")
    public Post findPost(@PathVariable("postId") Integer postId) {
        return posts.stream()
                .filter(post1 -> post1.getId() == postId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Post " + postId + " not found."));
    }
}
