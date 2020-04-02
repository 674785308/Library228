package com.etc.library.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String uri = request.getRequestURI();
        System.out.println("uri = " + uri);
        if (uri.endsWith("/login.html") || uri.endsWith("/login.do") || uri.contains("/js/")
                || uri.contains("/css/") || uri.contains("/fonts/") || uri.contains("/img/") || uri.contains("/upload/"))
            chain.doFilter(req, resp);
        else {
            HttpSession session = request.getSession();

            if (session.getAttribute("user") != null) {
                Cookie[] cookies = request.getCookies();
                if (cookies == null) {
                    response.sendRedirect("login.html");
                } else {
                    chain.doFilter(req, resp);
                }
            } else {
                response.sendRedirect("login.html");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
