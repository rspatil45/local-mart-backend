package com.rspatil45.first_project.ui.model.response;

import java.util.List;

import com.rspatil45.first_project.entity.ProductEntity;

public class UserResponseModel {
		private long id;
		private String publicUid;
		private String firstname;
		private String lastname;
		private String email;
		private String role;
		private List<ProductEntity> products;
		
		
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
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
		public List<ProductEntity> getProducts() {
			return products;
		}
		public void setProducts(List<ProductEntity> products) {
			this.products = products;
		}

}
