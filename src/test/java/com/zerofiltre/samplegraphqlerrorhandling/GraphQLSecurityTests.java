package com.zerofiltre.samplegraphqlerrorhandling;


import com.graphql.spring.boot.test.*;
import com.zerofiltre.samplegraphqlerrorhandling.error.*;
import com.zerofiltre.samplegraphqlerrorhandling.resolver.*;
import org.junit.jupiter.api.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.security.test.context.support.*;
import org.springframework.test.annotation.*;
import org.springframework.test.context.junit4.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GraphQLSecurityTests {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    private UserMutation userMutation;

    @Autowired
    private UserQuery userQuery;

    private static final String TEST_USERNAME = "john doe";
    private static final String TEST_PWD = "password";

    @Test
    @DisplayName("Access to Unsecured resource should be OK")
    public void unsecuredResourceOk() {
        userMutation.createUser(TEST_USERNAME, TEST_PWD);
    }


    @Test
    @DisplayName("Unauthenticated Access to secured resource should be unauthorized ")
    public void securedUnauthorizedAccessThrowsException() {
        Assertions.assertThrows(UnauthenticatedAccessException.class, () -> userQuery.getUser(TEST_USERNAME, TEST_PWD));
    }

    @Test
    @DisplayName("Authenticated Access to secured resource should be OK ")
    @WithMockUser(username = TEST_USERNAME)
    public void securedOk() {
        userMutation.createUser(TEST_USERNAME, TEST_PWD);
        //secured resource
        userQuery.getUser(TEST_USERNAME, TEST_PWD);
    }

    @Test
    @DisplayName("Unauthenticated Access to admin secured resource should be unauthorized ")
    public void adminUnauthorizedAccessThrowsException() {
        Assertions.assertThrows(UnauthenticatedAccessException.class, () -> userMutation.deleteUser(TEST_USERNAME));
    }


    @Test
    @DisplayName("Unauthorized Access to admin secured resource should be forbidden ")
    @WithMockUser(username = TEST_USERNAME)
    public void withoutAdminRoleThrowsException() {
        Assertions.assertThrows(UnauthenticatedAccessException.class, () -> userMutation.deleteUser(TEST_USERNAME));
    }

    @Test
    @DisplayName("Admin Authorized Access to admin secured resource should be OK ")
    @WithMockUser(username = TEST_USERNAME, roles = "ADMIN")
    public void adminRoleOk() {
        userMutation.deleteUser(TEST_USERNAME);

    }
}
