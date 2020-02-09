package com.zerofiltre.samplegraphqlerrorhandling.error;

public class UserAlreadyExistsException extends RuntimeException {


    public UserAlreadyExistsException(String message) {
        super(message);

    }

}
