package com.rspatil45.first_project.ui.model.request;

import com.rspatil45.first_project.entity.ProductEntity;

public class Cart {
	public ProductEntity item;
	int amount;

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public ProductEntity getItem() {
		return item;
	}
	public void setItem(ProductEntity item) {
		this.item = item;
	}
}
