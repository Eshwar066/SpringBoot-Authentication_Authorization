package com.example.demo_SpringSecurity.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


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
	
	@OneToMany(fetch=FetchType.EAGER)
//	@JoinTable(
//			name="user_role_function",
//			joinColumns = {@JoinColumn(name="user_id")},
//			inverseJoinColumns =  {@JoinColumn(name="role_id")}
//			)
	private Set<Role> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}
	
	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}
	
	public ApplicationUser() {
		super();
		this.authorities=new HashSet<Role>();
	}
	
	public ApplicationUser(Integer userId,String username, String password, Set<Role> authorities) {
		super();
		this.userId=userId;
		this.username=username;
		this.password=password;
		this.authorities=authorities;
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
