package com.user.payload.response;

import java.util.List;

public class LoginResponse {

	private Integer id;
	private String username;
	private String email;
	private String jwtToken;
	private List<String> roles;
	private String type = "Bearer";
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public LoginResponse(String jwtToken, Integer id, String username, String email, List<String> roles) {
		super();
		this.jwtToken = jwtToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
	
	
	
		
	
}
