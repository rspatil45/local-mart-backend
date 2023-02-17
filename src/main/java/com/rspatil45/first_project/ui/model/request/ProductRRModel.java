package com.rspatil45.first_project.ui.model.request;

import java.util.Date;
import com.rspatil45.first_project.entity.UserEntity;

public class ProductRRModel {
	/**
	 * 
	 */
	
	private String name;
	private String image;
	private String description;
	private double price;
	private int quantity;
	private UserEntity user;
	private String category;
	private String token;
	private String publicUid;
	private long id;
	private Date date;
	
	//-----------------------------------------------------------------
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getPublicUid() {
		return publicUid;
	}
	public void setPublicUid(String publicUid) {
		this.publicUid = publicUid;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory() {
		return category;
	}
	
}
