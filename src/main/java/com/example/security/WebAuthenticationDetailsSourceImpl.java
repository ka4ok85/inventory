package com.example.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component
public class WebAuthenticationDetailsSourceImpl implements AuthenticationDetailsSource<HttpServletRequest, JwtAuthenticationRequest> {

    @Override
    public JwtAuthenticationRequest buildDetails(HttpServletRequest context) {
        //return new JwtAuthenticationRequest(context);
    	//return new JwtAuthenticationRequest(username, password, storeId)
    	System.out.println("___#####_____");
    	System.out.println(context);
    	System.out.println("___#####_____");
    	return new JwtAuthenticationRequest();
    }
}
