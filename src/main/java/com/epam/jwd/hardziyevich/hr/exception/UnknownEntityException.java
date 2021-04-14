package com.epam.jwd.hardziyevich.hr.exception;

public class UnknownEntityException extends RuntimeException {

    private final String entityName;

    public UnknownEntityException(String entityName) {
        super();
        this.entityName = entityName;
    }

    @Override
    public String getMessage() {
        return entityName;
    }
}