package org.mirasruntime.catsgramtask1miras.controller;

import org.mirasruntime.catsgramtask1miras.exceptions.InvalidEmailException;
import org.mirasruntime.catsgramtask1miras.exceptions.NotFoundException;
import org.mirasruntime.catsgramtask1miras.exceptions.UserAlreadyExistException;
import org.mirasruntime.catsgramtask1miras.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/users")
public class UserController {

    private HashSet<User> users = new HashSet<>();

    @GetMapping
    public HashSet<User> getAllUsers() {
        return users;
    }

    @PostMapping
    public User postUser(@RequestBody User user) {

        if (user.getEmail() == null && user.getEmail().isBlank()) {
            throw new InvalidEmailException("Email can't be empty. Please fill it up!");
        }

        if (users.contains(user)) {
            throw new UserAlreadyExistException("This email is occupied by another user!");
        }

        users.add(user);
        return user;
    }

    @PutMapping
    public User putUser(@RequestBody User user) {

        if (user.getEmail() == null && user.getEmail().isBlank()) {
            throw new InvalidEmailException("Email can't be empty. Please fill it up!");
        }

        users.remove(user);
        users.add(user);

        return user;
    }

    @GetMapping("/user/{userMail}")
    public User getUser(@PathVariable("userMail") String userMail) {
        return users.stream()
                .filter(post1 -> post1.getEmail().equals(userMail))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User with email " + userMail + " not found."));
    }
}