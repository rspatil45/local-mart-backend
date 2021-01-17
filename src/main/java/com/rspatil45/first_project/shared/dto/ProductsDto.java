package com.rspatil45.first_project.shared.dto;

import java.io.Serializable;
import java.util.Date;
import com.rspatil45.first_project.entity.UserEntity;

public class ProductsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public long id;
	public UserEntity user;
	public String name;
	public String token;
	public String image;
	public int quantity;
	public String description;
	public double price;
	public String publicUid;
	public Date date;
	public String category;
	
	
	//-----------------------------------------------------------------------------------------------------
	

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public String getPublicUid() {
		return publicUid;
	}
	public void setPublicUid(String publicUid) {
		this.publicUid = publicUid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}


}
