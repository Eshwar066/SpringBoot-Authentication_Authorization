package com.example.demo_SpringSecurity;
//localhost:8000/user/            //get
//localhost:8000/auth/register   //post
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo_SpringSecurity.models.ApplicationUser;
import com.example.demo_SpringSecurity.models.Role;
import com.example.demo_SpringSecurity.repositary.RoleRepositary;

import com.example.demo_SpringSecurity.repositary.UserRepositary;

@SpringBootApplication
public class DemoSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringSecurityApplication.class, args);
		System.out.println("hello world");
	}
	
	@Bean
	CommandLineRunner runs(RoleRepositary roleRepositary,UserRepositary userRepositary,PasswordEncoder passwordEncoder) {
		return args->{
			if(roleRepositary.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole=roleRepositary.save(new Role("ADMIN"));
			roleRepositary.save(new Role("USER"));

			Set<Role> roles=new HashSet<>();
			roles.add(adminRole);

			ApplicationUser admin=new ApplicationUser(1,"admin",passwordEncoder.encode("password"),roles);
			userRepositary.save(admin);

		};
	}
}
