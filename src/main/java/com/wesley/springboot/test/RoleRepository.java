package com.wesley.springboot.test;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wesley.springboot.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
