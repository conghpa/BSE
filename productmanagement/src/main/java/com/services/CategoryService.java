package com.services;

import java.util.List;
import com.entities.Category;

public interface CategoryService {
	public List<Category> getAllCategory();
	public boolean deleteCategory(Category category);
	public boolean saveOrUdpateCategory(Category category);
}
