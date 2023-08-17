package com.example.demo_SpringSecurity.models;

import java.util.Collection;



import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="users")
public class ApplicationUser implements UserDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private Integer userId;
	
	public Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Column(unique=true)
	private String username;

	public ApplicationUser() {
		super();
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
	
	private String password;
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	


	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	


	

	
	public ApplicationUser(Integer userId,String username, String password) {
		super();
		this.userId=userId;
		this.username=username;
		this.password=password;

	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
