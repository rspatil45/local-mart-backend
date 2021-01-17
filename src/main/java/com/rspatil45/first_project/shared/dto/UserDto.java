package com.rspatil45.first_project.shared.dto;

import java.io.Serializable;
import java.util.List;

import com.rspatil45.first_project.entity.ProductEntity;

public class UserDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;

	private String firstname;
	private String email;
	private String role="user";
	private String lastname;
	private String password;
	private String publicUid;
	private String emailVerificationToken;
	private String encryptedPassword;
	private List<ProductEntity> products;
	private boolean emailVerificationStatus=false;
	
	public String getUserId() {
		return publicUid;
	}
	public void setUserId(String userId) {
		this.publicUid = userId;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public boolean isEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	public void setEmailVerificationStatus(boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<ProductEntity> getProducts() {
		return products;
	}
	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}
	
}
