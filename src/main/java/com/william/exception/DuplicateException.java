package com.william.exception;

public class DuplicateException extends RuntimeException {

    public DuplicateException(final String message) {
        super(message);
    }
}
