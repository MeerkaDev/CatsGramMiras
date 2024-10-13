package org.mirasruntime.catsgramtask1miras.controller;

import lombok.RequiredArgsConstructor;
import org.mirasruntime.catsgramtask1miras.model.User;
import org.mirasruntime.catsgramtask1miras.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Collection<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/{userEmail}")
    public User getUser(@PathVariable("userEmail") String userMail) {
        return userService.findUserByEmail(userMail);
    }
}