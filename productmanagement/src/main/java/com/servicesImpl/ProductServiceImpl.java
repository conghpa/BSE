package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProductDao;
import com.entities.Product;
import com.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDao productDao;
	
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return productDao.getAllProduct();
	}
	
	public boolean deleteProduct(Product product) {
		// TODO Auto-generated method stub
		return productDao.deleteProduct(product);
	}

	public boolean saveOrUdpateProduct(Product product) {
		// TODO Auto-generated method stub
		return productDao.saveOrUdpateProduct(product);
	}

	public List<Product> getAllProduct(Product product) {
		// TODO Auto-generated method stub
		return productDao.getAllProduct(product);
	}
	
}
