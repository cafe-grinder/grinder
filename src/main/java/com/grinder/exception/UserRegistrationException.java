package com.grinder.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;


@Getter
public class UserRegistrationException extends AuthenticationException {

    private String email;

    public UserRegistrationException(String message, String email) {
        super(message);
        this.email = email;
    }
}
