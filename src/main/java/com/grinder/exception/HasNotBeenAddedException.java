package com.grinder.exception;

public class HasNotBeenAddedException extends RuntimeException {
    public HasNotBeenAddedException(){}

    public HasNotBeenAddedException(String message) {
        super(message);
    }
}
