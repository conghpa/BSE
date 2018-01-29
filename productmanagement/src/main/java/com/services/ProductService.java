package com.services;

import java.util.List;

import com.entities.Product;

public interface ProductService {
	public List<Product> getAllProduct();
	public List<Product> getAllProduct(Product product);
	public boolean deleteProduct(Product product);
	public boolean saveOrUdpateProduct(Product product);
}
