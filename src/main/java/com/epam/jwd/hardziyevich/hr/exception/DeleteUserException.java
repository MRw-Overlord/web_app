package com.epam.jwd.hardziyevich.hr.exception;

public class DeleteUserException extends Exception {
    private final int userId;

    public DeleteUserException(int userId) {
        super();
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "Error delete user with id: " + userId;
    }
}

