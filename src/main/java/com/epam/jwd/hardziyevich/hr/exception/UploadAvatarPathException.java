package com.epam.jwd.hardziyevich.hr.exception;



public class UploadAvatarPathException extends Exception{
    private final int userId;

    public UploadAvatarPathException(int userId){
        super();
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "Error loading the image path into the database for the user with id: " + userId;
    }
}
