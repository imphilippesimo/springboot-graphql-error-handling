package com.zerofiltre.samplegraphqlerrorhandling.repository;

import com.zerofiltre.samplegraphqlerrorhandling.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    List<User> findByUsername(String username);
}
