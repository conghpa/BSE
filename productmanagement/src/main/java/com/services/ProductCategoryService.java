package com.services;

import java.util.List;

import com.entities.Category;
import com.entities.Product;
import com.entities.ProductCategory;

public interface ProductCategoryService {
	public List getAllProduct(Category category);
	public boolean addProductCategory(ProductCategory productCategory);
	public boolean updateProductCategory(List<ProductCategory> productCategoryList);
	public boolean deleteProductCategory(Category category);
	public boolean deleteProductCategory(Category category, List<Product> productList);
	public boolean addProductCategory(Category category, List<Product> productList);
}
