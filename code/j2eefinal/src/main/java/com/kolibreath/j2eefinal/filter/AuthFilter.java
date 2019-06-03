package com.kolibreath.j2eefinal.filter;


import com.kolibreath.j2eefinal.Common;
import com.kolibreath.j2eefinal.entity.User;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebFilter
@Order(value = 1)
public class AuthFilter implements Filter {


    private Set<String> prohibits =Collections.unmodifiableSet(new HashSet<>(
            Collections.singletonList("/right/show_application")));
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        if(prohibits.contains(path)) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Common.USER_INFO);
            if(user.getUserType() == Common.MANAGER){
                filterChain.doFilter(request,response);
            }else{
               ((HttpServletResponse) servletResponse).sendRedirect("error");
            }
        }else{
            filterChain.doFilter(request,response);
        }
    }
}
