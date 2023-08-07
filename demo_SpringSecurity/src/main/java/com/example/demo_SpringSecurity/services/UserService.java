package com.example.demo_SpringSecurity.services;
import com.example.demo_SpringSecurity.models.ApplicationUser;
import com.example.demo_SpringSecurity.models.Role;
import com.example.demo_SpringSecurity.repositary.UserRepositary;

import ch.qos.logback.core.encoder.Encoder;

import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepositary userRepositary;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("in user details service");
		
//		if(!username.equals("Ethan")) throw new UsernameNotFoundException("Not Ethan");
//		
//		Set<Role> roles=new HashSet<>();
//		roles.add(new Role(1,"USER"));
//		
//		return new ApplicationUser(1,"Ethan",encoder.encode("password"),roles);

		return userRepositary.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not valid"));
	}
	
	public ApplicationUser createUser(String username, String password) {
	    Set<Role> roles = new HashSet<Role>();
	    roles.add(new Role(1, "USER"));

	    String encodedPassword = passwordEncoder.encode(password);

	    return new ApplicationUser(1, username, encodedPassword, roles);
	}

}
