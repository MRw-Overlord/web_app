package com.epam.jwd.hardziyevich.hr.filter;

import com.epam.jwd.hardziyevich.hr.client.ClientType;
import com.epam.jwd.hardziyevich.hr.servlet.controller.ApplicationController;

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

        ClientType type = (ClientType) httpSession.getAttribute("UserType");
        if(type == null){
            type = ClientType.GUEST;
            httpSession.setAttribute("UserType", type);
            RequestDispatcher requestDispatcher = httpServletRequest.getServletContext()
                    .getRequestDispatcher("/controller?command="); //todo: make a command
            requestDispatcher.forward(httpServletRequest, httpServletResponse);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
