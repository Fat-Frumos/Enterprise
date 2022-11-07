package com.enterprise.rental.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class CharsetFilter implements Filter {
    private String encoding;

    public void init(FilterConfig config) throws ServletException {

        //read Parameter
        encoding = encoding
                != null
                ? config.getInitParameter("requestEncoding")
                : "UTF-8";
    }

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain next)

            throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        next.doFilter(request, response);
    }

    public void destroy() {
    }
}