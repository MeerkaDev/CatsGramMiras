package org.mirasruntime.catsgramtask1miras.controller;

import lombok.RequiredArgsConstructor;
import org.mirasruntime.catsgramtask1miras.model.Post;
import org.mirasruntime.catsgramtask1miras.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public List<Post> findAll(String sort, int size, int page) {

        int from = (page - 1) * size;

        return postService.findAll(sort, from, size);
    }

    @PostMapping(value = "/post")
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @GetMapping("/post/{postId}")
    public Post findPost(@PathVariable("postId") Integer postId) {
        return postService.findPostById(postId);
    }
}
