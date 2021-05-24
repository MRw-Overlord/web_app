package com.epam.jwd.hardziyevich.hr.command;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class WrappingRequestContext implements RequestContext {

    private final HttpServletRequest request;


    private WrappingRequestContext(HttpServletRequest request) {
        this.request = request;
    }

    public static RequestContext of(HttpServletRequest request) {
        return new WrappingRequestContext(request);
    }

    public ServletRequest getRequest() {
        return this.request;
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
        final String[] parameterValues = request.getParameterValues(name);
        List<String> params = null;
        if (parameterValues != null) {
            params = Arrays.asList(parameterValues);
        }
        return Optional.ofNullable(params);
    }

    @Override
    public void setCharacterEncoding(String s) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Part getPart(String name) throws IOException, ServletException {
        return request.getPart(name);
    }

    private HttpServletRequest _getHttpServletRequest() {
        return (HttpServletRequest) this.getRequest();
    }

}
