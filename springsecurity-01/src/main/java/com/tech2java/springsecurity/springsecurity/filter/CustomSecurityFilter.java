package com.tech2java.springsecurity.springsecurity.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class CustomSecurityFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("======Before Calling doFilter()======");
        chain.doFilter(request,response);
        System.out.println("======After Calling doFilter()======");
    }
}
