package com.epam.jwd.hardziyevich.hr.filter;

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
public class LoginProtectionFilter implements Filter {
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
        } else if (command.equalsIgnoreCase("show_profile_page") ||
                command.equalsIgnoreCase("bet") ||
                command.equalsIgnoreCase("logout") ||
                command.equalsIgnoreCase("show_bets_page")) {
            RequestDispatcher requestDispatcher;
            if (session != null) {
                if (session.getAttribute("login") == null) {
                    requestDispatcher = httpReq.getRequestDispatcher("/controller?command=show_login_page&redirect=true");
                    requestDispatcher.forward(servletRequest, servletResponse);
                    return;
                }
            } else {
                requestDispatcher = httpReq.getRequestDispatcher("/controller?command=show_login_page&redirect=true");
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
