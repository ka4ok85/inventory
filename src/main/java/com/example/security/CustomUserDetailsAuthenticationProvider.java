package com.example.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.NotFoundException;
import com.example.entity.Store;
import com.example.entity.User;
import com.example.entity.Userstore;
import com.example.repository.StoreRepository;
import com.example.repository.UserRepository;
import com.example.repository.UserstoreRepository;
import com.example.service.UserService;


public class CustomUserDetailsAuthenticationProvider extends DaoAuthenticationProvider {	

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserstoreRepository userstoreRepository;
    
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 	TODO Auto-generated method stub
		System.out.println("^^^^^^ custom authenticate attempt");
		JwtAuthenticationRequest details = (JwtAuthenticationRequest) authentication.getDetails();
		//MyWebAuthenticationDetails detais = (MyWebAuthenticationDetails) authentication.getDetails();
		System.out.println(details);
		return super.authenticate(authentication);
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		// TODO Auto-generated method stub
		//return false;
		System.out.println("^^^^^^ custom authenticate supports" + authentication.equals(UsernamePasswordAuthenticationToken.class));
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
		System.out.println("^^^^^^additionalAuthenticationChecks");
		
		
		
		
		super.additionalAuthenticationChecks(userDetails, authentication);
		JwtAuthenticationRequest details = (JwtAuthenticationRequest) authentication.getDetails();
		//MyWebAuthenticationDetails detais = (MyWebAuthenticationDetails) authentication.getDetails();
		System.out.println(details);
		System.out.println(details.getStoreId());
		
		String login = details.getUsername();
		User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new BadCredentialsException(login);
        }

        Long storeId = Long.parseLong(details.getStoreId(), 10);
        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new BadCredentialsException(storeId.toString());
        }
        System.out.println(user);
        System.out.println(store);        
        Userstore userstore = userstoreRepository.findByUserAndStoreAndStatus(user, store, Userstore.STATUS_ACTIVE);
        if (userstore == null) {
            throw new BadCredentialsException("Can't find active user " + user.getLogin() + " for store id=" + store.getId());
        }
        System.out.println(userstore);

        /*
        String defaultStoreId = "1";
		if (!details.getStoreId().equals(defaultStoreId)) {
			logger.debug("Authentication failed: wrong storeId were provided");
			throw new BadCredentialsException(messages.getMessage(
				"CustomUserDetailsAuthenticationProvider.badStoreId",
			"Bad StoreId"));
		}
		*/
/*
		throw new BadCredentialsException(messages.getMessage(
				"AbstractUserDetailsAuthenticationProvider.badCredentials",
				"Bad credentials"));
*/		
		//System.out.println("^^^^^^end");
	}
}
