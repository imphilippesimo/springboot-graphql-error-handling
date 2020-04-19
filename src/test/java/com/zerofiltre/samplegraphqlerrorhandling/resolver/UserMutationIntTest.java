package com.zerofiltre.samplegraphqlerrorhandling.resolver;

import com.graphql.spring.boot.test.*;
import com.zerofiltre.samplegraphqlerrorhandling.model.*;
import com.zerofiltre.samplegraphqlerrorhandling.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.security.test.context.support.*;

import java.io.*;

import static com.zerofiltre.samplegraphqlerrorhandling.utils.Constants.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@GraphQLTest
public class UserMutationIntTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    UserService userServiceMock;

    static User user = new User();


    @BeforeAll
    static void setUp() {
        user.setUsername(TEST_USERNAME);
        user.setPassword(TEST_PASSWORD);
    }

    @Test
    public void createUser() throws IOException {
        doReturn(user).when(userServiceMock).createUser(TEST_USERNAME, TEST_PASSWORD);
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/create-user.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.createUser.id")).isNotNull();
        assertThat(response.get("$.data.createUser.username")).isEqualTo(TEST_USERNAME);

    }

    @Test
    @WithMockUser(username = TEST_USERNAME, roles = "ADMIN")
    public void deleteUser() throws IOException {
        doReturn(user).when(userServiceMock).deleteUser(TEST_USERNAME);
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/delete-user.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.deleteUser.id")).isNotNull();
        assertThat(response.get("$.data.deleteUser.username")).isEqualTo(TEST_USERNAME);

    }
}


