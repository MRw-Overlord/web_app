package com.epam.jwd.hardziyevich.hr.filter;

import com.epam.jwd.hardziyevich.hr.pool.ActiveUserPool;

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

@WebFilter(urlPatterns = "/*")
public class UserStatusFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpRes = (HttpServletResponse) servletResponse;
        final HttpSession session = httpReq.getSession(false);
        RequestDispatcher requestDispatcher;
        final String command = httpReq.getParameter("command");
        if (command != null) {
            if (command.equalsIgnoreCase("logout")) {
                if (session != null) {
                    ActiveUserPool.getInstance().remove((String) session.getAttribute("login"));
                }
                requestDispatcher = httpReq.getRequestDispatcher("/controller?command=logout");
                requestDispatcher.forward(servletRequest, servletResponse);
                return;
            }
        }
        if (session != null) {
            final String login = (String) session.getAttribute("login");
            if (login != null && !ActiveUserPool.getInstance().isActive(login)) {
                requestDispatcher = httpReq.getRequestDispatcher("/controller?command=logout");
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
