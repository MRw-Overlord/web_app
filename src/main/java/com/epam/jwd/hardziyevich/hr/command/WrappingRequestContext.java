package com.epam.jwd.hardziyevich.hr.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class WrappingRequestContext implements RequestContext {

    private final HttpServletRequest request;

    private WrappingRequestContext(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setAttribute(String name, Object object) {
        request.setAttribute(name, object);
    }

    @Override
    public Object getAttribute(String name) {
        return request.getAttribute(name);
    }

    @Override
    public String getParameter(String name) {
        return request.getParameter(name);
    }

    @Override
    public void invalidateSession() {
        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @Override
    public void setSessionAttribute(String name, Object value) {
        final HttpSession session = request.getSession();
        session.setAttribute(name, value);
    }

    @Override
    public HttpSession getSession() {
        return request.getSession();
    }

    @Override
    public Optional<List<String>> getParameterValues(String name) {
        return Optional.empty();
    }

    public static RequestContext of(HttpServletRequest request) {
        return new WrappingRequestContext(request);
    }

}
