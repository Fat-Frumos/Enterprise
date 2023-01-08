package com.enterprise.rental.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter to Charset for windows-1251 is an object that performs filtering tasks
 * on either the request to a resource (a servlet or static content),
 * or on the response from a resource, or both. Implementation of <code>Filter</code>
 *
 * @author Pasha Polyak
 * @see javax.servlet.Filter
 */
public class CharsetFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();
    protected String encoding;

    /**
     * A filter configuration object used by a servlet container to pass information to a filter during initialization
     */
    protected FilterConfig filterConfig;

    /**
     * <p>The servlet container calls the init method exactly once after instantiating the filter.
     * The init method must complete successfully before the filter is asked to do any filtering work.</p>
     * A filter configuration object used by a servlet container
     * to pass information to a filter during initialization.
     *
     * @param config <code> FilterConfig</code> configuration and initialization
     * @see javax.servlet.FilterConfig
     */
    public void init(FilterConfig config) {

        filterConfig = config;
        LOGGER.log(Level.INFO, "Encoding filter initiated");
        //read Parameter
        encoding = encoding
                != null
                ? config.getInitParameter("encoding")  //"requestEncoding"
                : "UTF-8";
    }

    /**
     * Method allows the Filter to Set character encoding windows-1251 on the request and response
     *
     * @param request  the <code>ServletRequest</code> an object to provide client request information to a servlet
     * @param response the <code>ServletResponse</code> object contains the filter's response
     * @param next     the <code>FilterChain</code> for invoking the next filter or the resource
     * @throws IOException      if an I/O related error has occurred during the processing
     * @throws ServletException if an exception occurs that interferes with the
     *                          filter's normal operation
     * @see javax.servlet.FilterChain
     */
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain next)
            throws IOException, ServletException {

        String charEncoding = getCharsetFromContentType(request.getContentType());
        String encodingRequest = (charEncoding == null) ? encoding : charEncoding;
        //set Encoding Character request
        if (encodingRequest != null) {
            request.setCharacterEncoding(encodingRequest);
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getParameter("sessionLocale") != null) {
            httpRequest.getSession().setAttribute(
                    "lang", httpRequest.getParameter("sessionLocale"));
        }
        //set ContentType response
        response.setContentType("text/html; charset=windows-1251");
        next.doFilter(request, response);
    }

    /**
     * Parse Charset From Content Type
     *
     * @param contentType a content Type Charset
     * @return {@code String charset} Location
     */
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

    /**
     * Clean up all the filters supplied,
     * calling each one's destroy method in turn,
     * but in reverse order.
     *
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void destroy() {
        LOGGER.log(Level.INFO, "Encoding filter destroyed");
        encoding = null;
        filterConfig = null;
    }
}