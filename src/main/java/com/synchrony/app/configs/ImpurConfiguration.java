package com.synchrony.app.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration()
@ConfigurationProperties(value="impur")
public class ImpurConfiguration {

	private String clientid;
	private String clientsecret;
	private String oauthurl;
	private String refreshurl;
	
	private String accessToken;
	private String refreshToken;
	
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getClientSecret() {
		return clientsecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientsecret = clientSecret;
	}
	public String getOauthUrl() {
		return oauthurl;
	}
	public void setOauthUrl(String oauthUrl) {
		this.oauthurl = oauthUrl;
	}
	public String getClientsecret() {
		return clientsecret;
	}
	public void setClientsecret(String clientsecret) {
		this.clientsecret = clientsecret;
	}
	public String getOauthurl() {
		return oauthurl;
	}
	public void setOauthurl(String oauthurl) {
		this.oauthurl = oauthurl;
	}
	public String getRefreshurl() {
		return refreshurl;
	}
	public void setRefreshurl(String refreshurl) {
		this.refreshurl = refreshurl;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
}
