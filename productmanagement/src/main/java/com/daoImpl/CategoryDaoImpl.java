package com.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.common.Constant;
import com.dao.CategoryDao;
import com.entities.Category;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao{
	
	@Autowired
	SessionFactory sessionFactory;
	public List<Category> getAllCategory() {
		return sessionFactory.getCurrentSession().createQuery("FROM Category Where deleteFlg <> '" + Constant.deleteFlg +"'").list();
	}

	public Category getCategory(Category category) {
		return (Category) sessionFactory.getCurrentSession().createQuery("FROM Category Where categoryCode = '" + category.getCategoryCode() + "'").list().get(0);
	}
	
	public boolean deleteCategory(Category category) {
		try {
			category = getCategory(category);
			category.setDeleteFlg(Constant.deleteFlg);
			sessionFactory.getCurrentSession().saveOrUpdate(category);
			
			//session.getCurrentSession().saveOrUpdate(category);
			return true;
		}catch(Exception ex) {
			return false;
		}
		
	}

	public boolean saveOrUdpateCategory(Category category) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(category);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

}
