package com.enterprise.rental.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {
    protected String encoding;
    protected FilterConfig filterConfig;

    public void init(FilterConfig config) throws ServletException {

        filterConfig = config;

        //read Parameter
        encoding = encoding
                != null
                ? config.getInitParameter("encoding")  //"requestEncoding"
                : "UTF-8"; // "text/html; charset=windows-1251"
    }

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain next)
            throws IOException, ServletException {

        String encodingRequest = selectEncoding(request);

        if (encodingRequest != null) {
            request.setCharacterEncoding(encodingRequest);
        }

        response.setContentType("text/html; charset=windows-1251");
        next.doFilter(request, response);
    }

    private String selectEncoding(ServletRequest request) {
        String charEncoding = getCharsetFromContentType(request.getContentType());
        return (charEncoding == null) ? encoding : charEncoding;
    }

    public static String getCharsetFromContentType(String contentType) {

        if (contentType == null || !contentType.contains(";")) {
            return null;
        }

        int semicolon = contentType.indexOf(";");

        String afterSemi = contentType.substring(semicolon + 1);
        int charsetLocation = afterSemi.indexOf("charset=");

        return charsetLocation != -1
                ? afterSemi.substring(charsetLocation + 8).trim()
                : null;
    }

    public void destroy() {
        encoding = null;
        filterConfig = null;
    }
}