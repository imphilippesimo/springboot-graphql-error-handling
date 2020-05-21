package com.zerofiltre.samplegraphqlerrorhandling;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SampleGraphqlErrorHandlingApplication {
    public static void main(String[] args) {
        SpringApplication.run(SampleGraphqlErrorHandlingApplication.class, args);
    }
}
