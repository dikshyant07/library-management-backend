package com.library.management.system.libraryManagementSystem.exceptions;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
