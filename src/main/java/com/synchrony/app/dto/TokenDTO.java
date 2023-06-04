package com.synchrony.app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "token_table")
public class TokenDTO {
	
	
	@Id
	@GeneratedValue
	private Long id;
	private String state;
	private String access_token;
	private Long expires_in;
	private String token_type;
	private String refresh_token;
	private String account_username;
	private Long updateon;
	private String account_id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Long getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getAccount_username() {
		return account_username;
	}
	public void setAccount_username(String account_username) {
		this.account_username = account_username;
	}
	public Long getUpdateon() {
		return updateon;
	}
	public void setUpdateon(Long updateon) {
		this.updateon = updateon;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

}
