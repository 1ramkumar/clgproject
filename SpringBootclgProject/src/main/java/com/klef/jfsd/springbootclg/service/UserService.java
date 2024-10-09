package com.klef.jfsd.springbootclg.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.klef.jfsd.springbootclg.model.User;
import com.klef.jfsd.springbootclg.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
  User save(UserRegistrationDto registrationDto);
	
	
}
