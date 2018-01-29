package com.dao;

import java.util.List;
import com.entities.*;
public interface ProductDao {
	public List<Product> getAllProduct();
	public List<Product> getAllProduct(Product product);
	public boolean deleteProduct(Product product);
	public boolean saveOrUdpateProduct(Product product);
}
