package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.Entity.Users;
import com.example.demo.repository.UserRepository;


public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Fetching student from database
		Users user =  this.repository.findUserByName(username);
		System.out.println("Parsed Username: " + username);
		
		if(user == null) {
			throw new UsernameNotFoundException("No user found with the email " + username);
		}
		
//		System.out.println(user);
		UserDetails user1 = new CustomUserDetails(user);
		return user1;
	}


}
