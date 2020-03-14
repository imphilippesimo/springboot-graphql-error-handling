package com.zerofiltre.samplegraphqlerrorhandling.resolver;

import com.coxautodev.graphql.tools.*;
import com.zerofiltre.samplegraphqlerrorhandling.model.*;
import com.zerofiltre.samplegraphqlerrorhandling.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class UserQuery implements GraphQLQueryResolver {

    @Autowired
    private UserService userService;


    public User getUser(String username, String password) {
        return userService.getUser(username, password);
    }
}
