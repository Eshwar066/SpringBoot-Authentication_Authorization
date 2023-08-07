package com.example.demo_SpringSecurity.repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_SpringSecurity.models.ApplicationUser;

@Repository
public interface UserRepositary extends JpaRepository<ApplicationUser, Integer> {
	Optional<ApplicationUser> findByUsername(String username);
}
