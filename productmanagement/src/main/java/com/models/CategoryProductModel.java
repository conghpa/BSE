package com.models;

import java.util.List;

import com.entities.Category;
import com.entities.Product;

public class CategoryProductModel {
	private Category category;
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	private List<Product> productList;
}
