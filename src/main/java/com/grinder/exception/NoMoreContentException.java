package com.grinder.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoMoreContentException extends RuntimeException {

    public NoMoreContentException(String message) {
        super(message);
    }
}