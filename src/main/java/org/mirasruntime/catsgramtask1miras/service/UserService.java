package org.mirasruntime.catsgramtask1miras.service;

import org.mirasruntime.catsgramtask1miras.exception.InvalidEmailException;
import org.mirasruntime.catsgramtask1miras.exception.UserAlreadyExistException;
import org.mirasruntime.catsgramtask1miras.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final Map<String, User> users = new HashMap<>();

    public Collection<User> findAll() {
        return users.values();
    }

    public User findUserByEmail(String email) {

        if(email.isBlank()) {
            return null;
        }

        return users.get(email);
    }

    public User create(User user) {

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Email can't be empty. Please fill it up!");
        }

        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("This email is occupied by another user!");
        }

        users.put(user.getEmail(), user);
        return user;
    }

    public User update(User user) {

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new InvalidEmailException("Email can't be empty. Please fill it up!");
        }

        users.put(user.getEmail(), user);
        return user;
    }
}
