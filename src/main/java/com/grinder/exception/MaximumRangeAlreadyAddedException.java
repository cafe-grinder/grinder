package com.grinder.exception;

public class MaximumRangeAlreadyAddedException extends RuntimeException {
    public MaximumRangeAlreadyAddedException() {}
    public MaximumRangeAlreadyAddedException(String message) {
        super(message);
    }
}
