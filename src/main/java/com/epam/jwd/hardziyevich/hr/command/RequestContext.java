package com.epam.jwd.hardziyevich.hr.command;

public interface RequestContext {

    void setAttribute(String name, Object obj);
    void invalidateSession();
    void setSessionAttribute(String name, Object value);
    Object getAttribute(String name);
}
