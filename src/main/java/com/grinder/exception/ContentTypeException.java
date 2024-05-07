package com.grinder.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ContentTypeException extends RuntimeException {

    public ContentTypeException(String message) {
        super(message);
    }
}
