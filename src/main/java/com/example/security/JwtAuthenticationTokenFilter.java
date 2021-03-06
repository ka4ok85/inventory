package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String tokenHeader = "Authorization";
    //private String storeId;
/*
    @Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final String storeId = request.getParameter("storeId");
        System.out.println(storeId);
        request.getSession().setAttribute("dbValue", storeId);

        return super.attemptAuthentication(request, response); 
    }
*/
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
System.out.println("______JwtAuthenticationTokenFilter begin");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
System.out.println(request.toString());
        String authToken = httpRequest.getHeader(this.tokenHeader);
System.out.println(authToken);
        // authToken.startsWith("Bearer ")
        // String authToken = header.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        System.out.println("authtoken username:" + username);
        
        String store = jwtTokenUtil.getStoreFromToken(authToken);
        System.out.println("authtoken store:" + store);
        System.out.println("FILTER 0 " + SecurityContextHolder.getContext().getAuthentication());
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	System.out.println("FILTER 1");
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
            	System.out.println("FILTER 2");
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("FILTER 2 END");
            }
        }
System.out.println("______JwtAuthenticationTokenFilter chain");
//System.out.println(request);
//System.out.println(request.getAttribute("username"));
//System.out.println(request.getParameter("username"));

//authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));

        chain.doFilter(request, response);
    }
}