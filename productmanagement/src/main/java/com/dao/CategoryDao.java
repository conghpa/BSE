package com.dao;

import java.util.List;
import com.entities.Category;

public interface CategoryDao {
	public List<Category> getAllCategory();
	public boolean deleteCategory(Category category);
	public boolean saveOrUdpateCategory(Category category);
}
