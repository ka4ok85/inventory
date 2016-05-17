package com.example.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service("userDetailsService")
public class MemberServiceImpl implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.entity.User user = userRepository.findByLogin(username);
        System.out.println(username);
        if (user == null) {
        	System.out.println("exception");
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
        	System.out.println(user);
            //return JwtUserFactory.create(user);
        	//private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
                //return authorities.stream()
                        //.map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                        //.collect(Collectors.toList());
            //}
        	//List<Authority>
        	SimpleGrantedAuthority x = new SimpleGrantedAuthority("ROLE_USER");
        	//SimpleGrantedAuthority x = new SimpleGrantedAuthority(username);
        	List<GrantedAuthority> y = new ArrayList<GrantedAuthority>();
        	y.add(x);
        	return new JwtUser(
                    user.getId(),
                    user.getLogin(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPassword(),
                    //mapToGrantedAuthorities(user.getAuthorities()),
                    y,
                    true
            );
        }
    }	
	
	/*
    //@Autowired
    //MemberRepository memberRepository;

    private List<GrantedAuthority> buildUserAuthority(String role) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        setAuths.add(new SimpleGrantedAuthority(role));
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
        return result;
    }

    //private User buildUserForAuthentication(Object member, List<GrantedAuthority> authorities) {
        //return new User("email123", "password123", true, true, true, true, authorities);
    //}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Getting access details from employee dao !!");
 
        // Ideally it should be fetched from database and populated instance of
        // #org.springframework.security.core.userdetails.User should be returned from this method
        //UserDetails user = new User(username, "password", true, true, true, true, new GrantedAuthority[]{ new GrantedAuthorityImpl("ROLE_USER") });
        List<GrantedAuthority> authorities = buildUserAuthority("Role");
        return new User("email123", "$2a$04$UY.Z1wgpamPj25x.bRikmuXIjSnAdOBsCeM8oUuwOrhNEn3YpGyVC", true, true, true, true, authorities);
        //return user;
    }
*/
}