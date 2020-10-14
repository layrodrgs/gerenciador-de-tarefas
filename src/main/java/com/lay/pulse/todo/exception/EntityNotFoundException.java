package com.lay.pulse.todo.exception;

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException() {
        super("Project not found");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
