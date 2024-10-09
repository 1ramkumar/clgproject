package com.klef.jfsd.springbootclg.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springbootclg.model.Role;
import com.klef.jfsd.springbootclg.model.User;
import com.klef.jfsd.springbootclg.repository.UserRepo;
import com.klef.jfsd.springbootclg.web.dto.UserRegistrationDto;
//service layer 
@Service
public class UserServiceImpl implements UserService {

	
	private final  UserRepo userRe;
	//  here this  is a construction based   injection 
	
	private final BCryptPasswordEncoder passwordEncoder;
	
//  here this  is a construction based   injection
	 @Autowired
	    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, UserRepo userRe) {
	        this.passwordEncoder = passwordEncoder;
	        this.userRe = userRe;
	    }
	
	
	// we created a save method ,which save user registartion data to the db.
	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getFirstName(),
				registrationDto.getLastName () ,registrationDto.getEmail() , passwordEncoder.encode(registrationDto.getPassword()) , 
				Arrays.asList(new Role("ROLE_USER")));
		
		return userRe.save(user);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRe.findByEmail(username);
		if(user== null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
	return  new org.springframework.security.core.userdetails.User(user.getEmail() ,user.getPassword() , mapRolesToAuthorities(user.getRoles())) ;
// user is the class which implements user details.
	}
	
	// this method mapps roles to authorities .
	//we convert roles into steam , on top of stream we just map role  , we coverted role into simpleGRantedauthroity , we pass a role->name to simpleGRANTEDauthority class
	// and finally we colltecd stream into a list and returned it.
	private Collection <? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)	{
		 return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	
}
