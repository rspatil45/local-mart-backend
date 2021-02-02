package com.rspatil45.first_project.ui.model.request;

import java.util.List;

import com.rspatil45.first_project.entity.UserEntity;

public class OrderRequestModel {
	private UserEntity user;
	private List<Cart> cart;
	private String code;
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public List<Cart> getCart() {
		return cart;
	}
	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
