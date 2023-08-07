package com.example.demo_SpringSecurity.services;

import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo_SpringSecurity.models.ApplicationUser;
//import com.example.demo_SpringSecurity.models.LoginResponseDTO;
import com.example.demo_SpringSecurity.models.Role;
import com.example.demo_SpringSecurity.repositary.RoleRepositary;
import com.example.demo_SpringSecurity.repositary.UserRepositary;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthenticationService {
	
	@Autowired
	private UserRepositary userRepositary;
	
	@Autowired
	private RoleRepositary roleRepositary;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
	

	

    
	public ApplicationUser registerUser(String username, String password) {
		String encodedPassword=passwordEncoder.encode(password);
		Role userRole=roleRepositary.findByAuthority("USER").get();
		Set<Role> authorities=new HashSet<>();
		authorities.add(userRole);
		
		return userRepositary.save(new ApplicationUser(0,username,encodedPassword,authorities));
	}
	

 }

//@Autowired
//private TokenService tokenService;

// private final DaoAuthenticationProvider authenticationProvider;
//
//    @Autowired
//    public AuthenticationService(PasswordEncoder passwordEncoder) {
//        this.authenticationProvider = new DaoAuthenticationProvider();
//        this.authenticationProvider.setPasswordEncoder(passwordEncoder);
//    }

//public LoginResponseDTO loginUser(String username,String password) {
//	
//	try {
//		
//		Authentication auth= authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(username,password)
//				);
	
//		String token=tokenService.generateJwt(auth);
//		
//		return new LoginResponseDTO(userRepositary.findByUsername(username).get(),token);
//	}catch(AuthenticationException e) {
//		
//		return new LoginResponseDTO(null," ");
//		
//	}
//	
//}
