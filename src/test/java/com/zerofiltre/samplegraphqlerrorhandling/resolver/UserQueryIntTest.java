package com.zerofiltre.samplegraphqlerrorhandling.resolver;

import com.graphql.spring.boot.test.*;
import com.zerofiltre.samplegraphqlerrorhandling.model.*;
import com.zerofiltre.samplegraphqlerrorhandling.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.security.test.context.support.*;

import static com.zerofiltre.samplegraphqlerrorhandling.utils.Constants.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@GraphQLTest
public class UserQueryIntTest {


    @MockBean
    UserService userServiceMock;
    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    @WithMockUser(username = TEST_USERNAME)
    public void getUser() throws Exception {

        User user = new User();
        user.setId(TEST_ID);
        user.setUsername(TEST_USERNAME);
        user.setPassword(TEST_PASSWORD);
        doReturn(user).when(userServiceMock).getUser(TEST_USERNAME, TEST_PASSWORD);

        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/get-user.graphql");
        assertThat(response.isOk()).isTrue();
        assertThat(response.get("$.data.getUser.id")).isNotNull();
        assertThat(response.get("$.data.getUser.id")).isEqualTo(String.valueOf(user.getId()));
        assertThat(response.get("$.data.getUser.username")).isEqualTo(TEST_USERNAME);
    }
}
