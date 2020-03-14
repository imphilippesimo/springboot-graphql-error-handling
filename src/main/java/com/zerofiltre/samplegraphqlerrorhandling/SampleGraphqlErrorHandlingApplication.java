package com.zerofiltre.samplegraphqlerrorhandling;

import com.zerofiltre.samplegraphqlerrorhandling.error.handler.*;
import graphql.execution.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.*;
import org.springframework.context.annotation.*;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SampleGraphqlErrorHandlingApplication {

    public static void main(String[] args) {

        SpringApplication.run(SampleGraphqlErrorHandlingApplication.class, args);

    }

}
