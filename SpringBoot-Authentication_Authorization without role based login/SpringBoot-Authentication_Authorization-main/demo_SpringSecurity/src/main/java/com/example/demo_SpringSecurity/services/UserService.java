package com.example.demo_SpringSecurity.services;


import com.example.demo_SpringSecurity.repositary.UserRepositary;






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

		return userRepositary.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not valid"));
	}
	


}
