package com.example.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;

@Component
public class WebAuthenticationDetailsSourceImpl implements AuthenticationDetailsSource<HttpServletRequest, JwtAuthenticationRequest> {

    @Override
    public JwtAuthenticationRequest buildDetails(HttpServletRequest context) {
        //return new JwtAuthenticationRequest(context);
    	//return new JwtAuthenticationRequest(username, password, storeId)
    	System.out.println("___#####_____");
    	//System.out.println(context);
    	//System.out.println(context.getParameter("username"));
    	//System.out.println(context.getParameter("password"));
    	//System.out.println(context.getParameter("storeId"));

    	/*
    	BufferedReader reader;
		try {
			reader = context.getReader();
			Gson gson = new Gson();
			JwtAuthenticationRequest myBean = gson.fromJson(reader, JwtAuthenticationRequest.class);
	    	System.out.println(myBean);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	*/
    	Gson gson = new Gson();
    	String json = new String();
    	String output = new String();
    	BufferedReader br;
    	
    	StringBuffer buffer = new StringBuffer(16384);


    	JwtAuthenticationRequest jwtAuthenticationRequest = new JwtAuthenticationRequest();
        
    	try {
			br = new BufferedReader(new InputStreamReader(context.getInputStream()));
	        while ((output = br.readLine()) != null) {
	            buffer.append(output);
	        }			
			json = buffer.toString();
			JwtAuthenticationRequest myBean = gson.fromJson(json, JwtAuthenticationRequest.class);
			System.out.println(myBean);
			jwtAuthenticationRequest = myBean;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	/*
    	
    	Enumeration<String> y = context.getAttributeNames();
    	System.out.println(y);
    	while (y.hasMoreElements()) {
    		System.out.println(y.nextElement());
    		
    	}
    	
    	Enumeration<String> x = context.getParameterNames();
    	System.out.println(x);
    	while (x.hasMoreElements()) {
    		System.out.println(x.nextElement());
    		
    	}

    	Enumeration<String> z = context.getHeaderNames();
    	System.out.println(z);
    	String zz;
    	while (z.hasMoreElements()) {
    		zz = z.nextElement();
    		System.out.println(zz);
    		System.out.println(context.getHeader(zz));
    		
    	}

    	//System.out.println(x.nextElement());
    	
    	
    	
    	System.out.println("___#####_____");
    	//return new JwtAuthenticationRequest();
    	
    	JwtAuthenticationRequest jwtAuthenticationRequest = new JwtAuthenticationRequest();
    	jwtAuthenticationRequest.setUsername("qqq");
    	jwtAuthenticationRequest.setPassword("www");
    	jwtAuthenticationRequest.setStoreId("111");
    	*/
    	return jwtAuthenticationRequest;
    }
}
