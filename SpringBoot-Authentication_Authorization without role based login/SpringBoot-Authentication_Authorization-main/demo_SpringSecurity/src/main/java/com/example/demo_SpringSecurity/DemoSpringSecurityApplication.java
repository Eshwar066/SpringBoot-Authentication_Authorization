package com.example.demo_SpringSecurity;



import com.example.demo_SpringSecurity.services.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo_SpringSecurity.models.ApplicationUser;


import com.example.demo_SpringSecurity.repositary.UserRepositary;

@SpringBootApplication
public class DemoSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringSecurityApplication.class, args);
		System.out.println("hello world");
	}

	@Bean
	CommandLineRunner runs(UserRepositary userRepositary, PasswordEncoder passwordEncoder, AuthenticationService authenticationService) {
		return args -> {
			ApplicationUser user = userRepositary.findByUsername("user").orElse(null);
			if (user == null) {
				user = authenticationService.registerUser("user", "password");
			}
		};
	}
}

//localhost:8000/user/            //get
//localhost:8000/auth/register   //post
