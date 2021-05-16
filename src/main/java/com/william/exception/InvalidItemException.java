package com.william.exception;

public class InvalidItemException extends RuntimeException {

    public InvalidItemException(final String message) {
        super(message);
    }
}
