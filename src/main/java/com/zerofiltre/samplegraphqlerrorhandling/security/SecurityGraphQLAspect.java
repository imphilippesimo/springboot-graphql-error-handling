package com.zerofiltre.samplegraphqlerrorhandling.security;

import com.zerofiltre.samplegraphqlerrorhandling.error.*;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Aspect
@Component
@Order(1)
public class SecurityGraphQLAspect {

    /**
     * All graphQLResolver methods can be called only by authenticated user.
     *
     * @Unsecured annotated methods are excluded
     */

    @Before("allGraphQLResolverMethods() && isDefinedInApplication() && !isMethodAnnotatedAsUnsecured()")
    public void doSecurityCheck() {
        if (SecurityContextHolder.getContext() == null ||
                SecurityContextHolder.getContext().getAuthentication() == null ||
                !SecurityContextHolder.getContext().getAuthentication().isAuthenticated() ||
                AnonymousAuthenticationToken.class.isAssignableFrom(SecurityContextHolder.getContext().getAuthentication().getClass())) {
            throw new UnauthenticatedAccessException("Sorry, you should log in first to do that!");
        }
    }

    /**
     * @AdminSecured annotated methods can be called only by admin authenticated user.
     */
    @Before("isMethodAnnotatedAsAdminUnsecured()")
    public void doAdminSecurityCheck() {
        if (!isAuthorized()) {
            throw new UnauthenticatedAccessException("Sorry, you do not have enough rights to do that!");
        }
    }


    /**
     * Matches all beans that implement {@link com.coxautodev.graphql.tools.GraphQLResolver} as
     * {@code UserMutation}, {@code UserQuery}
     * extend GraphQLResolver interface
     */
    @Pointcut("target(com.coxautodev.graphql.tools.GraphQLResolver)")
    private void allGraphQLResolverMethods() {
        //leave empty
    }

    /**
     * Matches all beans in com.zerofiltre.samplegraphqlerrorhandling package
     */
    @Pointcut("within(com.zerofiltre.samplegraphqlerrorhandling..*)")
    private void isDefinedInApplication() {
        //leave empty
    }

    /**
     * Any method annotated with @Unsecured
     */
    @Pointcut("@annotation(com.zerofiltre.samplegraphqlerrorhandling.security.Unsecured)")
    private void isMethodAnnotatedAsUnsecured() {
        //leave empty
    }

    /**
     * Any method annotated with @AdminSecured
     */
    @Pointcut("@annotation(com.zerofiltre.samplegraphqlerrorhandling.security.AdminSecured)")
    private void isMethodAnnotatedAsAdminUnsecured() {
        //leave empty
    }

    private boolean isAuthorized() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority auth : authorities) {
                if (auth.getAuthority().equals("ROLE_ADMIN"))
                    return true;
            }
        }
        return false;
    }
}
