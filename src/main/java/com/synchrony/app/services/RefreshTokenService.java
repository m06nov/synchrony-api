package com.synchrony.app.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.synchrony.app.configs.ImpurConfiguration;
import com.synchrony.app.dto.TokenDTO;
import com.synchrony.app.repositories.TokenRepository;

@Service
public class RefreshTokenService {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ImpurConfiguration config;

	@Autowired
	private TokenRepository tokenRepository;
	public void refreshToken() {
		Iterable<TokenDTO> findAll = tokenRepository.findAll();
		if(findAll != null)
		{
			List<TokenDTO> tokenList = new ArrayList<>();
			findAll.forEach(dto->{
				tokenList.add(dto);
			});

			if(tokenList.size()>0) {
				TokenDTO tokenDTO = tokenList.get(0);

				HashMap<String, Object> requestBody = new HashMap<>();
				requestBody.put("client_id", config.getClientid());
				requestBody.put("client_secret", config.getClientsecret());
				requestBody.put("refresh_token", tokenDTO.getRefresh_token());
				requestBody.put("grant_type", "refresh_token");

				ResponseEntity<TokenDTO> response = 
						restTemplate.postForEntity(
								config.getRefreshurl(), 
								requestBody, 
								TokenDTO.class);


				TokenDTO responseBody = response.getBody();

				if (responseBody != null) {
					tokenDTO.setAccess_token(responseBody.getAccess_token());
					tokenDTO.setRefresh_token(responseBody.getRefresh_token());
					tokenDTO.setExpires_in(responseBody.getExpires_in());
					tokenDTO.setUpdateon(System.currentTimeMillis());
					config.setAccessToken(responseBody.getAccess_token());
					config.setRefreshToken(responseBody.getRefresh_token());
					tokenRepository.save(tokenDTO);
				}
			}
		}
	}

	@Scheduled(cron = "* * * * * *")
	public void refreshExpiredToken() {
		Iterable<TokenDTO> findAll = tokenRepository.findAll();
		if(findAll != null)
		{
			List<TokenDTO> tokenList = new ArrayList<>();
			findAll.forEach(dto->{
				tokenList.add(dto);
			});

			if(!tokenList.isEmpty()) {
				TokenDTO tokenDTO = tokenList.get(0);
				if((tokenDTO.getExpires_in()+tokenDTO.getUpdateon()) <= (System.currentTimeMillis()-60000)) {
					HashMap<String, Object> requestBody = new HashMap<>();
					requestBody.put("client_id", config.getClientid());
					requestBody.put("client_secret", config.getClientsecret());
					requestBody.put("refresh_token", tokenDTO.getRefresh_token());
					requestBody.put("grant_type", "refresh_token");

					ResponseEntity<TokenDTO> response = 
							restTemplate.postForEntity(
									config.getRefreshurl(), 
									requestBody, 
									TokenDTO.class);

					TokenDTO responseBody = response.getBody();

					if (responseBody != null) {
						tokenDTO.setAccess_token(responseBody.getAccess_token());
						tokenDTO.setRefresh_token(responseBody.getRefresh_token());
						tokenDTO.setExpires_in(responseBody.getExpires_in());
						tokenDTO.setUpdateon(System.currentTimeMillis());
						config.setAccessToken(responseBody.getAccess_token());
						config.setRefreshToken(responseBody.getRefresh_token());
						tokenRepository.save(tokenDTO);
					}
				}
			}
		}
	}
}
