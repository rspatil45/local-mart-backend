package com.rspatil45.first_project.ui.model.response;

public class UserLoginResponseModel {
	private String publicUid;
	private String email;
	private String firstname;
	private String token;
	private String role;
	private long id;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPublicUid() {
		return publicUid;
	}
	public void setPublicUid(String publicUid) {
		this.publicUid = publicUid;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

}
