package com.synchrony.app.configs;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synchrony.app.services.RefreshTokenService;

//import com.synchrony.app.repositories.TokenRepository;

@Component
public class ImpurOnLoad {
	@Autowired
	private RefreshTokenService service;

	@PostConstruct
	public void init() {
		service.refreshToken();
	}
}
