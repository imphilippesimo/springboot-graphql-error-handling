package com.zerofiltre.samplegraphqlerrorhandling.security;

import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;

@EnableWebSecurity
//Enabling security checking at the method level with annotation support
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //authorize all graphQL queries as we will filter request at the resolvers level
                .antMatchers("/graphql").permitAll()

                //authorize requests from graphql related apps that we will need
                .antMatchers("/graphiql").permitAll()
                .antMatchers("/vendor/**").permitAll()

                //any other requests should be authenticated
                .anyRequest().authenticated();
    }
}
