package com.epam.jwd.hardziyevich.hr.filter.old;


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

@WebFilter(urlPatterns = {"/controller"}, servletNames = {"ApplicationController"})
public class ServletSecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession httpSession = httpServletRequest.getSession();
        String command = httpServletRequest.getParameter("command");

        Role role = (Role) httpSession.getAttribute("UserType");
        /*if(role == null){
            role = Role.GUEST;
            httpSession.setAttribute("UserType", role);
            RequestDispatcher requestDispatcher = httpServletRequest.getServletContext()
                    .getRequestDispatcher("/controller?command="); //todo: make a command
            requestDispatcher.forward(httpServletRequest, httpServletResponse);
        }*/
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
