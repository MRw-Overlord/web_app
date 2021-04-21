package com.epam.jwd.hardziyevich.hr.command;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface RequestContext {

    void setAttribute(String name, Object obj);

    Object getAttribute(String name);

    String getParameter(String name);

    void invalidateSession();

    void setSessionAttribute(String name, Object value);

    HttpSession getSession();

    Optional<List<String>> getParameterValues(String name);

    void setCharacterEncoding(String s);
}
