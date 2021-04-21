package com.epam.jwd.hardziyevich.hr.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", initParams = {
        @WebInitParam(name ="encoding", value="UTF-8", description = "Encoding Param")}
        )
public class EncodingFilter implements Filter {

    private static final String ENCODING_DEFAULT = "UTF-8";

    private static final String ENCODING_INIT_PARAM_NAME = "encoding";

    private String encoding;

    public void destroy(){

    }

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws ServletException, IOException{
        String contentType = req.getContentType();
        if (contentType != null)
            req.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException{
        encoding = config.getInitParameter(ENCODING_INIT_PARAM_NAME);
        if (encoding == null)
            encoding = ENCODING_DEFAULT;
    }
}

