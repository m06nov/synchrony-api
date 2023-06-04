package com.synchrony.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synchrony.app.dto.UserDetailsDTO;
import com.synchrony.app.repositories.UserDetailsRepository;
import com.synchrony.app.request.pojo.UserDetailsRequest;

@RestController
@RequestMapping(value = "/user")
public class UserDetailsController {
	
	@Autowired
	private UserDetailsRepository userDetailsRepository; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/get/{userId}")
	public UserDetailsDTO getUser(@PathVariable("userId") Long userId) {
		Optional<UserDetailsDTO> user = userDetailsRepository.findById(userId);
		return user.orElse(null);
	}
	
	@GetMapping("/get-all")
	public List<UserDetailsDTO> getAllUser() {
		List<UserDetailsDTO> users = userDetailsRepository.findAll();
		return users;
	}
	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long createUserDetails(@RequestBody UserDetailsRequest userDetailsRequest){
		UserDetailsDTO save = userDetailsRepository.save(convert(userDetailsRequest));
		return save.getId();
	}
	


	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long updateUserDetails(@RequestBody UserDetailsRequest userDetailsRequest){
		UserDetailsDTO save = userDetailsRepository.save(convert(userDetailsRequest));
		return save.getId();
	}
	
	private UserDetailsDTO convert(UserDetailsRequest userDetailsRequest) {
		UserDetailsDTO detailsDTO = new UserDetailsDTO();
		detailsDTO.setEmail(userDetailsRequest.getEmail());
		detailsDTO.setFirstName(userDetailsRequest.getFirstName());
		detailsDTO.setLastName(userDetailsRequest.getLastName());
		detailsDTO.setMobile(userDetailsRequest.getMobile());
		detailsDTO.setPassword(passwordEncoder.encode(userDetailsRequest.getPassword()));
		detailsDTO.setUsername(userDetailsRequest.getUsername());
		return detailsDTO;
	}
}
