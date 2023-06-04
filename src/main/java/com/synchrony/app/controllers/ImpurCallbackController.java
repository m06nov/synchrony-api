package com.synchrony.app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synchrony.app.configs.ImpurConfiguration;
import com.synchrony.app.dto.TokenDTO;
import com.synchrony.app.repositories.TokenRepository;
import com.synchrony.app.services.RefreshTokenService;

@RestController
@RequestMapping("/impur")
public class ImpurCallbackController {

	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private RefreshTokenService service;
	
	@Autowired
	private ImpurConfiguration configuration;
	
	
	@GetMapping(value = "/callback", produces = MediaType.TEXT_HTML_VALUE)
	public String myCallBack(HttpServletRequest httpServletRequest) {
		
		String serverURL = "https://localhost:8443/impur/storevalue";
		
		
		return "<script>"
				+ "var params = {}, queryString = location.hash.substring(1),\r\n"
				+ "    regex = /([^&=]+)=([^&]*)/g, m;\r\n"
				+ "while (m = regex.exec(queryString)) {\r\n"
				+ "  params[decodeURIComponent(m[1])] = decodeURIComponent(m[2]);\r\n"
				+ "}\r\n"
				+ "// And send the token over to the server\r\n"
				+ "var req = new XMLHttpRequest();\r\n"
				+ "// consider using POST so query isn't logged\r\n"
				+ "req.open('GET', '"+serverURL+"?' + queryString, true);\r\n"
				+ "req.onreadystatechange = function (e) {\r\n"
				+ "  if (req.readyState == 4) {\r\n"
				+ "     if(req.status == 200){\r\n"
				+ "       window.location = params['state']\r\n"
				+ "   }\r\n"
				+ "  else if(req.status == 400) {\r\n"
				+ "        alert('There was an error processing the token.')\r\n"
				+ "    }\r\n"
				+ "    else {\r\n"
				+ "      alert('something else other than 200 was returned')\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "};\r\n"
				+ "req.send(null);"
				+ "</script>";
	}
	
	@GetMapping(value= "/storevalue", produces = MediaType.TEXT_HTML_VALUE)
	public String storevalue(
			@RequestParam("access_token") String access_token,
			@RequestParam("expires_in") Long expires_in,
			@RequestParam("token_type") String token_type,
			@RequestParam("refresh_token") String refresh_token,
			@RequestParam("account_username") String account_username,
			@RequestParam("account_id") String account_id
			
			) {
		TokenDTO tokenDTO = new TokenDTO();
		Iterable<TokenDTO> findAll = tokenRepository.findAll();
		if(findAll != null) {
			List<TokenDTO> dtos = new ArrayList<TokenDTO>();
			findAll.forEach(value->{
				dtos.add(value);
			});
			if(dtos.size()>0) {
				tokenDTO = dtos.get(0);
			}
		}
		
		tokenDTO.setAccess_token(access_token);
		tokenDTO.setAccount_username(account_username);
		tokenDTO.setExpires_in(expires_in);
		tokenDTO.setRefresh_token(refresh_token);
		tokenDTO.setToken_type(token_type);
		tokenDTO.setAccount_id(account_id);
		tokenDTO.setUpdateon(System.currentTimeMillis());
		configuration.setAccessToken(access_token);
		configuration.setRefreshToken(refresh_token);
		
		tokenRepository.save(tokenDTO);
		return "success";
	}
	
	@GetMapping(value= "/refresh-token")
	public String refreshToken() {
		service.refreshToken();
		return "success";
	}
	
}
