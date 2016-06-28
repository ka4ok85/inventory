package com.example.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class UsernamePasswordStoreAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
/*
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String tokenHeader = "Authorization";
    //private String storeId;

    @Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    	System.out.println("______UsernamePasswordStoreAuthenticationFilter attemptAuthentication");
    	final String storeId = request.getParameter("storeId");
        System.out.println(storeId);
        //request.getSession().setAttribute("dbValue", storeId);

        return super.attemptAuthentication(request, response); 
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	System.out.println("______UsernamePasswordStoreAuthenticationFilter super");
    	super.doFilter(request, response, chain);

        System.out.println("______UsernamePasswordStoreAuthenticationFilter chain");
        //chain.doFilter(request, response);
    }
    */
}
