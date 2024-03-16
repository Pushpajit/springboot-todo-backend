package com.example.demo.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.Entity.Users;



public class CustomUserDetails implements UserDetails{

	private Users users;
	
	public CustomUserDetails(Users users) {
		super();
		this.users = users;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return AuthorityUtils.createAuthorityList("ADMIN");
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.users.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.users.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { //8765432109
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

	@Override
	public String toString() {
		return "CustomUserDetails [users=" + users + "]";
	}

	

}
