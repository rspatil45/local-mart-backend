package com.rspatil45.first_project.entity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//jsonidentityinfo annotation used to prevent recursion , here property field is the only field which we will show to avoid recursion
// so it this property can't be null
@Entity(name="users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class UserEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
		
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
	private String publicUid;
	
	@Column()
	private String verifyCode;
	
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
	@JsonIgnoreProperties("user")
	private List<ProductEntity> products;

	
	//------------------------------------------------------------------------------
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPublicUid() {
		return publicUid;
	}

	public void setPublicUid(String publicUid) {
		this.publicUid = publicUid;
	}

	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
 	 
	
}	
