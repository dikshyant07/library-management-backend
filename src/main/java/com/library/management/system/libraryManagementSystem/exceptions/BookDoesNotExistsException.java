package com.library.management.system.libraryManagementSystem.exceptions;

public class BookDoesNotExistsException extends RuntimeException {
    public BookDoesNotExistsException(String message) {
        super(message);
    }
}
