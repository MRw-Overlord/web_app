package com.epam.jwd.hardziyevich.hr.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(urlPatterns = {"/*"})
public class SetLocaleFilter implements Filter {

    private static final String ALLOWED_LOCALE_REGEX = "(en-US)|(ru-RU)|(ja-JP)";
    private static final String DEFAULT_LOCALE = "en-US";
    private static final String COOKIE_LOCALE_NAME = "lang";
    private static final String COOKIE_REQUEST_PARAMETER = "cookieLocale";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;
        String locale = req.getParameter(COOKIE_REQUEST_PARAMETER);
        Cookie localeCookie;
        if (req.getCookies() != null) {
            localeCookie = Arrays.stream(req.getCookies())
                    .filter(e -> e.getName().equalsIgnoreCase(COOKIE_LOCALE_NAME))
                    .findFirst().orElse(new Cookie(COOKIE_LOCALE_NAME, DEFAULT_LOCALE));
        } else {
            localeCookie = new Cookie(COOKIE_LOCALE_NAME, DEFAULT_LOCALE);
        }
        Cookie cookie;
        if (locale != null && locale.matches(ALLOWED_LOCALE_REGEX)) {
            cookie = new Cookie(COOKIE_LOCALE_NAME, locale);
            res.setHeader("Refresh", "0;" + req.getContextPath());
        } else {
            cookie = localeCookie;
        }
        cookie.setMaxAge(Integer.MAX_VALUE);
        res.addCookie(cookie);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
