package com.example.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.NotFoundException;
import com.example.entity.Store;
import com.example.entity.Userstore;
import com.example.repository.StoreRepository;
import com.example.repository.UserRepository;
import com.example.repository.UserstoreRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service("userDetailsService")
public class MemberServiceImpl implements UserDetailsService {

	private String storeId;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private StoreRepository storeRepository;

	@Autowired
	private UserstoreRepository userstoreRepository;

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
System.out.println("MemberServiceImpl username " + username);
System.out.println("MemberServiceImpl storeId " + storeId);
    	com.example.entity.User user = userRepository.findByLogin(username);
    	System.out.println("MemberServiceImpl " + user);
        if (user == null) {
        	System.out.println("MemberServiceImpl user is null");
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
        	//System.out.println("MemberServiceImpl user is not null");
        	System.out.println("MemberServiceImpl user is not null. storeId " + storeId);
            //Store store = storeRepository.findOne(Long.parseLong(storeId));
            //if (store == null) {
//            	System.out.println("store");
            	//throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            //}

            //Userstore userstore = userstoreRepository.findByUserAndStoreAndStatus(user, store, "active");
            //if (userstore == null) {
//            	System.out.println("userstore");
            	//throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            //}
            //Set<Userstore> userStoresList = user.getUserstores();
        	//for (Userstore userstore : userStoresList) {
//				System.out.println("storeId" + userstore.getStore().getId());
			//}
        	//return JwtUserFactory.create(user);
            //private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
                //return authorities.stream()
                        //.map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                        //.collect(Collectors.toList());
            //}
            //List<Authority>
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
            //SimpleGrantedAuthority x = new SimpleGrantedAuthority(username);
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
            grantedAuthorityList.add(simpleGrantedAuthority);
            return new JwtUser(
                    user.getId(),
                    user.getLogin(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPassword(),
                    storeId,
                    //mapToGrantedAuthorities(user.getAuthorities()),
                    grantedAuthorityList,
                    true
            );
        }
    }

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}	

    
}