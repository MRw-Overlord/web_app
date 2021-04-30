package com.epam.jwd.hardziyevich.hr.exception;

public class WriteAvatarImgDbException extends Exception {
    private final int userId;

    public WriteAvatarImgDbException(int userId){
        super();
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "Error writing an image to the database for a user with id: " + userId;
    }
}
