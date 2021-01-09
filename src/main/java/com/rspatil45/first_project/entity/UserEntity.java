package com.rspatil45.first_project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="users")
public class UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
//	@Column(nullable=false)
//	private String userId="1";
//	
	

	@Column(nullable=false, length=30)
	private String firstname;
	
	@Column(nullable=false, length=30)
	private String lastname;
	
	@Column(unique=true,nullable=false, length=50)
	private String email;
	
	@Column(nullable=false, length=10)
	private String role="user";
	
	@Column(nullable=false)
	private String password;
	

	
	@Column(nullable=false)
	private String userId;



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	 	 

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



	
}	
