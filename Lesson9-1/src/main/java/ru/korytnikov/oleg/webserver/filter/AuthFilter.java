package ru.korytnikov.oleg.webserver.filter;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("REQUESTED URL " + request.getRequestURI());

        HttpSession session = request.getSession();

        if (session.getAttribute("isLoggedIn") != null) {
            boolean isLoggedIn = (boolean) session.getAttribute("isLoggedIn");
            if (!isLoggedIn) {
                notAuthorized(response);
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            notAuthorized(response);
        }
    }

    @Override
    public void destroy() {

    }

    public static void notAuthorized(HttpServletResponse response) throws IOException {
        Map<String, Boolean> result = new HashMap<>();
        result.put("result", false);
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(result));
    }
}
