package com.zerofiltre.samplegraphqlerrorhandling.resolver;

import com.coxautodev.graphql.tools.*;
import com.zerofiltre.samplegraphqlerrorhandling.model.*;
import com.zerofiltre.samplegraphqlerrorhandling.security.*;
import com.zerofiltre.samplegraphqlerrorhandling.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class UserMutation implements GraphQLMutationResolver {

    @Autowired
    UserService userService;

    @Unsecured
    public User createUser(String username, String password) {
        return userService.createUser(username, password);
    }

    @AdminSecured
    public int deleteUser(int id) {
        return userService.deleteUser(id);
    }
}
