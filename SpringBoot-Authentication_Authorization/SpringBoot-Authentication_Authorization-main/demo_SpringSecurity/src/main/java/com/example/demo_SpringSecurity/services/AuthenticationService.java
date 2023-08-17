package com.example.demo_SpringSecurity.services;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo_SpringSecurity.models.ApplicationUser;
import com.example.demo_SpringSecurity.models.LoginResponseDTO;
import com.example.demo_SpringSecurity.repositary.UserRepositary;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthenticationService {
	
	@Autowired
	private UserRepositary userRepositary;
	

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;
	

    
	public ApplicationUser registerUser(String username, String password) {
		String encodedPassword=passwordEncoder.encode(password);


		return userRepositary.save(new ApplicationUser(1,username,encodedPassword));
	}
	public LoginResponseDTO loginUser(String username,String password) {

		try {
			Authentication auth= authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username,password)
			);

			String token=tokenService.generateJwt(auth);

			return new LoginResponseDTO(userRepositary.findByUsername(username).get(),token);
		}catch(AuthenticationException e) {

			return new LoginResponseDTO(null,"");

		}
	}

 }






