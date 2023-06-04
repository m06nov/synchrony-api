package com.synchrony.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.synchrony.app.dto.UserDetailsDTO;
import com.synchrony.app.repositories.UserDetailsRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDetailsRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserDetailsDTO> user = repo.findByUsername(username);
		if(user.isPresent()) {
			return User
					.withUsername(user.get().getUsername())
					.password(user.get().getPassword())
					.roles("USER_ROLE")
					.build();
		}
		return null;
	}

}
