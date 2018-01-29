package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProductCategoryDao;
import com.entities.Category;
import com.entities.Product;
import com.entities.ProductCategory;
import com.services.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

	@Autowired
	ProductCategoryDao productCategoryDao;
	
	public List<Product> getAllProduct(Category category) {
		// TODO Auto-generated method stub
		return productCategoryDao.getAllProduct(category);
	}

	public boolean addProductCategory(ProductCategory productCategory) {
		// TODO Auto-generated method stub
		return productCategoryDao.addProductCategory(productCategory);
	}

	public boolean updateProductCategory(List<ProductCategory> productCategoryList) {
		// TODO Auto-generated method stub
		return productCategoryDao.updateProductCategory(productCategoryList);
	}

	public boolean deleteProductCategory(Category category) {
		// TODO Auto-generated method stub
		return productCategoryDao.deleteProductCategory(category);
	}

	public boolean deleteProductCategory(Category category, List<Product> productList) {
		// TODO Auto-generated method stub
		return productCategoryDao.deleteProductCategory(category, productList);
	}

	public boolean addProductCategory(Category category, List<Product> productList) {
		// TODO Auto-generated method stub
		return productCategoryDao.addProductCategory(category, productList);
	}
	
}
