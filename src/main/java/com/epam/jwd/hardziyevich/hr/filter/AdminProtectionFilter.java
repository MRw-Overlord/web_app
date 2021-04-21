package com.epam.jwd.hardziyevich.hr.filter;

import com.epam.jwd.hardziyevich.hr.model.entity.Role;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/controller")
public class AdminProtectionFilter implements Filter {
    private static final int FORBIDDEN_STATUS = 403;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpRes = (HttpServletResponse) servletResponse;
        final HttpSession session = httpReq.getSession(false);
        final String command = httpReq.getParameter("command");
        if (command == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (command.equalsIgnoreCase("show_admin_page") ||
                command.equalsIgnoreCase("ban_recruiter") ||
                command.equalsIgnoreCase("ban_user") ||
                command.equalsIgnoreCase("appoint_recruiter")) {
            RequestDispatcher requestDispatcher;
            if (session != null) {
                if (session.getAttribute("role") != Role.ADMIN.name()) {
                    httpRes.setStatus(FORBIDDEN_STATUS); //todo: create error page 403
                    requestDispatcher = httpReq.getRequestDispatcher("/controller?command=show_forbidden_page"); //todo: create this command
                    requestDispatcher.forward(servletRequest, servletResponse);
                    return;
                }
            } else {
                httpRes.setStatus(FORBIDDEN_STATUS);
                requestDispatcher = httpReq.getRequestDispatcher("/controller?command=show_forbidden_page");
                requestDispatcher.forward(servletRequest, servletResponse);
                return;
            }
        } else if( command.equalsIgnoreCase("show_recruiter_page") ||
                command.equalsIgnoreCase("create_vacancy") ||
                command.equalsIgnoreCase("delete_vacancy") ||
                command.equalsIgnoreCase("response_user")){
            RequestDispatcher requestDispatcher;
            if (session != null) {
                if (session.getAttribute("role") != Role.HR.name()) {
                    httpRes.setStatus(FORBIDDEN_STATUS); //todo: create error page 403
                    requestDispatcher = httpReq.getRequestDispatcher("/controller?command=show_forbidden_page"); //todo: create this command
                    requestDispatcher.forward(servletRequest, servletResponse);
                    return;
                }
            } else {
                httpRes.setStatus(FORBIDDEN_STATUS);
                requestDispatcher = httpReq.getRequestDispatcher("/controller?command=show_forbidden_page");
                requestDispatcher.forward(servletRequest, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
