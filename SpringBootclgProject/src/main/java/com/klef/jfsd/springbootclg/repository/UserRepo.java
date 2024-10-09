package com.klef.jfsd.springbootclg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springbootclg.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
// we write a method, which retrive  object  by email address.
	User findByEmail(String email);
	
}
