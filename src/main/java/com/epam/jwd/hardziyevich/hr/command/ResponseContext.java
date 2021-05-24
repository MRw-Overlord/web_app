package com.epam.jwd.hardziyevich.hr.command;

public interface ResponseContext {

    String getPage();

    boolean isRedirect();

    default String getUrlToRedirect() {
        return "/";
    }

}
