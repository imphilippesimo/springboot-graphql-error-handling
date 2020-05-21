package com.zerofiltre.samplegraphqlerrorhandling.service;

import com.zerofiltre.samplegraphqlerrorhandling.error.*;
import com.zerofiltre.samplegraphqlerrorhandling.model.*;
import com.zerofiltre.samplegraphqlerrorhandling.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.util.concurrent.atomic.*;

import static com.zerofiltre.samplegraphqlerrorhandling.utils.Constants.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceIntTests {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    private User user = new User();


    @BeforeAll
    void init() {
        user.setUsername(TEST_USERNAME);
        user.setPassword(TEST_PASSWORD);
        repository.save(user);
    }

    @Test
    public void deleteAUserShouldBeOK() {
        User user0 = new User();
        user0.setUsername("to be");
        user0.setPassword("deleted");
        repository.save(user0);
        service.deleteUser(user0.getId());
    }

    @Test
    public void createUserIsOk() {
        User user = service.createUser("another", "user");
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void createUserWithExistingCredentialsThrowsException() {
        Assertions.assertThrows(UserAlreadyExistsException.class, () -> service.createUser(TEST_USERNAME, TEST_PASSWORD));

    }

    @Test
    public void getAUserByCredentialsIsOk() {
        AtomicReference<User> user1 = new AtomicReference<>();
        Assertions.assertDoesNotThrow(() -> user1.set(service.getUser(TEST_USERNAME, TEST_PASSWORD)));
        assertThat(user1.get().getId()).isNotNull();
    }

    @Test
    public void getAUserByFakeCredentialsThrowsException() {
        Assertions.assertThrows(UserNotFoundException.class, () -> service.getUser("unknown", "user"));
    }

    @Test
    public void getUsersByUsernameIsOK() {
        assertThat(service.userExists(TEST_USERNAME)).isTrue();

    }

}
