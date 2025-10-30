package com.library.management.system.libraryManagementSystem.exceptions;

public class RefreshTokenDoesNotExistsException extends RuntimeException {
    public RefreshTokenDoesNotExistsException(String message) {
        super(message);
    }
}
