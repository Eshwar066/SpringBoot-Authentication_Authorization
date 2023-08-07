package com.example.demo_SpringSecurity.repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_SpringSecurity.models.Role;

@Repository
public interface RoleRepositary extends JpaRepository<Role, Integer >{
   Optional<Role> findByAuthority(String Authority);
}
