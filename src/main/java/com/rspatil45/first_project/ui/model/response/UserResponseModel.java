package com.rspatil45.first_project.ui.model.response;

public class UserResponseModel {
		private String userId;
		private String firstname;
		private String lastname;
		private String email;
		private String role;
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
		private String message;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}

}
