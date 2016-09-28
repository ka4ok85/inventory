package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Store;
import com.example.repository.StoreRepository;
import com.example.security.JwtAuthenticationRequest;
import com.example.security.JwtTokenUtil;
import com.example.security.JwtUser;
import com.example.service.StoreService;
import com.example.security.JwtAuthenticationResponse;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationRestController {


    private String tokenHeader = "Authorization";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MemberServiceImpl userDetailsService;

    @Autowired
    private StoreService storeService; 

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    //@RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ResponseEntity<?> createAuthenticationToken(HttpServletRequest request, /*@RequestBody JwtAuthenticationRequest authenticationRequest,*/ Device device) throws AuthenticationException {
    //public ResponseEntity<?> createAuthenticationToken(HttpServletRequest request, Device device) throws AuthenticationException {
System.out.println("---start standard auth contoller");

	// Perform the security
		try {
			WebAuthenticationDetailsSourceImpl webAuthenticationDetailsSourceImpl = new WebAuthenticationDetailsSourceImpl();
			JwtAuthenticationRequest authenticationRequest = webAuthenticationDetailsSourceImpl.buildDetails(request);
			System.out.println("---contoller jwtRequest" + authenticationRequest);
			UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(),
					authenticationRequest.getPassword()
					//"",""
			);

			token1.setDetails(authenticationRequest);
			Authentication authentication = authenticationManager.authenticate(token1);
			SecurityContextHolder.getContext().setAuthentication(authentication);


			// Reload password post-security so we can generate token

			userDetailsService.setStoreId(authenticationRequest.getStoreId());
			Store store = storeService.findStore(Long.valueOf(authenticationRequest.getStoreId()));
			userDetailsService.setStoreName(store.getName());
			JwtUser userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails, device);
        

			// Return the token
			return ResponseEntity.ok(new JwtAuthenticationResponse(token));
		} catch (BadCredentialsException e) {
			System.out.println("BadCredentialsException");
			JwtAuthenticationBadLoginResponse jwtAuthenticationBadLoginResponse = new JwtAuthenticationBadLoginResponse();
			jwtAuthenticationBadLoginResponse.setMessage("Bad credentials provided");
			System.out.println(jwtAuthenticationBadLoginResponse);
			return ResponseEntity.ok(jwtAuthenticationBadLoginResponse);
		}
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, jwtTokenUtil.getCreatedDateFromToken(token)/*user.getLastPasswordResetDate()*/)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
