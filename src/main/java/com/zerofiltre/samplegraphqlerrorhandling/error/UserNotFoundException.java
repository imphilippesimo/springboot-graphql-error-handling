package com.zerofiltre.samplegraphqlerrorhandling.error;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

}
