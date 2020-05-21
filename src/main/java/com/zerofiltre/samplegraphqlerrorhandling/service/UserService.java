package com.zerofiltre.samplegraphqlerrorhandling.service;

import com.zerofiltre.samplegraphqlerrorhandling.error.*;
import com.zerofiltre.samplegraphqlerrorhandling.model.*;
import com.zerofiltre.samplegraphqlerrorhandling.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserService {

    List<User> users = new ArrayList<>();
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(String username, String password) {
        if (userExists(username))
            throw new UserAlreadyExistsException("A user already exists with this username, please try another one");
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        return repository.save(user);
    }

    public User getUser(String username, String password) {
        Optional<User> user = repository.findByUsernameAndPassword(username, password);
        if (user.isPresent())
            return user.get();
        throw new UserNotFoundException("We were unable to find a user with the provided credentials", "username");
    }

    public boolean userExists(String username) {
        return repository.findByUsername(username).size() > 0;
    }

    public int deleteUser(int id) {
        repository.deleteById(id);
        return id;
    }
}
