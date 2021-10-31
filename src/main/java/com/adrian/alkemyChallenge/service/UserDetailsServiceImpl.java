package com.adrian.alkemyChallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adrian.alkemyChallenge.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.adrian.alkemyChallenge.model.User usuario = userRepository.findByUsername(username);
		UserBuilder userBuilder = null;
		if (usuario != null) {
			userBuilder = User.withUsername(username);
			userBuilder.disabled(false);
			userBuilder.password(usuario.getPassword());
			userBuilder.authorities(new SimpleGrantedAuthority("ROL_USER"));
		} else {
			throw new UsernameNotFoundException("Usuario no registrado");
		}
		return userBuilder.build();
	}
}
