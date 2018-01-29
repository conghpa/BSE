package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CategoryDao;
import com.entities.Category;
import com.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryDao categoryDao;

	public List<Category> getAllCategory() {
		
		return categoryDao.getAllCategory();
	}

	public boolean deleteCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.deleteCategory(category);
	}

	public boolean saveOrUdpateCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryDao.saveOrUdpateCategory(category);
	}
	
}
