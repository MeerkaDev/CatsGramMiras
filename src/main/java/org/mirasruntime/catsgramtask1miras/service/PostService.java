package org.mirasruntime.catsgramtask1miras.service;

import lombok.RequiredArgsConstructor;
import org.mirasruntime.catsgramtask1miras.exception.PostNotFoundException;
import org.mirasruntime.catsgramtask1miras.exception.UserNotFoundException;
import org.mirasruntime.catsgramtask1miras.model.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserService userService;

    private final List<Post> posts = new ArrayList<>();

    public List<Post> findAll(String sort, int size, int from) {

        List<Post> postsResult = new ArrayList<>();

        postsResult = posts.stream()
                .sorted((o1, o2) -> {
                    if (sort.equals("asc")) {
                        return o1.getCreationDate().compareTo(o2.getCreationDate());
                    } else if (sort.equals("desc")) {
                        return o2.getCreationDate().compareTo(o1.getCreationDate());
                    }
                    return 0;
                })
                .skip(from)
                .limit(size)
                .toList();

        return postsResult;
    }

    public Post findPostById(Integer postId) {
        return posts.stream()
                .filter(post -> post.getId().equals(postId))
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException("Post " + postId + " not found."));
    }

    public Post create(Post post) {

        if (userService.findUserByEmail(post.getAuthor()) == null) {
            throw new UserNotFoundException("User with email " + post.getAuthor() + " not found.");
        }
        ;

        posts.add(post);
        return post;
    }
}
