package com.grinder.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException(String message) {
        super(message);
    }
}
