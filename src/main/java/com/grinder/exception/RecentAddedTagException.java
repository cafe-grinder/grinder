package com.grinder.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RecentAddedTagException extends RuntimeException {

    public RecentAddedTagException(String message) {
        super(message);
    }
}
