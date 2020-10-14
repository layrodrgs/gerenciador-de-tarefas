package com.lay.pulse.todo.exception;

public class DuplicatedEntityException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DuplicatedEntityException() {
        super("Project already exists");
    }

    public DuplicatedEntityException(String message) {
        super(message);
    }
}
