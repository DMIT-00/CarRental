package com.dmit.exception;

public class InvalidOperation extends RuntimeException {
    public InvalidOperation(String message) {
        super(message);
    }
}
