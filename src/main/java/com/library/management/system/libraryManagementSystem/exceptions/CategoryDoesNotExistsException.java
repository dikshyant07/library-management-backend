package com.library.management.system.libraryManagementSystem.exceptions;

public class CategoryDoesNotExistsException extends RuntimeException {
    public CategoryDoesNotExistsException(String message) {
        super(message);
    }
}
