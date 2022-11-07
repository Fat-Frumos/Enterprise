//package com.enterprise.rental.filter;
//
//import com.enterprise.rental.controller.UserServlet;
//import com.enterprise.rental.entity.User;
//import org.apache.log4j.Logger;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebFilter("/filter")
//public class SecurityFilter implements Filter {
//    private static final Logger log = Logger.getLogger(SecurityFilter.class);
//
//    private ServletContext context;
//
//    public void init(FilterConfig fConfig) throws ServletException {
//        this.context = fConfig.getServletContext();
//        this.context.log("AuthenticationFilter initialized");
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        String uri = req.getRequestURI();
//        this.context.log("Requested Resource::"+uri);
//
//        HttpSession session = req.getSession(false);
//
//        if(session == null && !(uri.endsWith("jsp") || uri.endsWith("login"))){
//            this.context.log("Unauthorized access request");
//            res.sendRedirect("/login");
//        }else{
//            User user = (User) session.getAttribute("user");
//            log.info(String.format(user.getRole()));
//
//            // pass the request along the filter chain
//            chain.doFilter(request, response);
//        }
//
//    }
//
//
//
//    public void destroy() {
//        //close any resources here
//    }
//
//}
//
////
////import javax.servlet.*;
////import javax.servlet.annotation.WebFilter;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpSession;
////import java.io.IOException;
////
////@WebFilter("/*")
////public class SecurityFilter implements Filter {
////    @Override
////    public void init(FilterConfig filterConfig) throws ServletException {
////    }
////
////    @Override
////    public void doFilter(
////            ServletRequest servletRequest,
////            ServletResponse servletResponse,
////            FilterChain chain) throws IOException, ServletException {
////
////        HttpServletRequest request = (HttpServletRequest) servletRequest;
////        HttpSession session = request.getSession();
////
////        boolean login = (session != null && session.getAttribute("user") != null);
////
////        String uri = request.getRequestURI();
////
////        boolean css = uri.startsWith("/WEB-INF/classes/templates/css/");
////
////        if (login && page(uri)) {
////            request.getRequestDispatcher("/cars").forward(servletRequest, servletResponse);
////        } else if (!login && required(uri)) {
////            request.getRequestDispatcher("/").forward(servletRequest, servletResponse);
////        } else if (css) {
////            chain.doFilter(servletRequest, servletResponse);
////        } else {
////            servletRequest.setAttribute("redirect", request.getRequestURL().toString());
////            chain.doFilter(servletRequest, servletResponse);
////        }
////    }
////
////    private boolean required(String uri) {
////
////        return !uri.equals("/cars") && !uri.equals("/login");
////    }
////
////    private boolean page(String requestURI) {
////        return requestURI.equals("/");
////    }
////
////    @Override
////    public void destroy() {
////
////    }
////}
