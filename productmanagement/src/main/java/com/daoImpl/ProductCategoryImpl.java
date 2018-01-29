package com.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.common.Constant;
import com.common.Utils;
import com.dao.ProductCategoryDao;
import com.entities.Category;
import com.entities.Product;
import com.entities.ProductCategory;

@Repository
@Transactional
public class ProductCategoryImpl implements ProductCategoryDao {

	@Autowired
	SessionFactory sessionFactory;

	public List getAllProduct(Category category) {
		// TODO Auto-generated method stub
		StringBuilder sqlCmd = new StringBuilder("select p FROM ProductCategory pc join pc.product p ");
		sqlCmd.append(" WHERE pc.categoryCode = :categoryCode");
		sqlCmd.append(" AND pc.startDate <= :nowDate and pc.dueDate >= :nowDate AND pc.deleteFlg <> :deleteFlg");
		sqlCmd.append(" AND p.deleteFlg <> :deleteFlg");
		Query query = sessionFactory.getCurrentSession().createQuery(sqlCmd.toString());
		query.setParameter("categoryCode", category.getCategoryCode());
		query.setParameter("deleteFlg", Constant.deleteFlg);
		query.setParameter("nowDate", Utils.getSqlDateNow());
		return query.list();
	}

	public boolean addProductCategory(ProductCategory productCategory) {
		// TODO Auto-generated method stub
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(productCategory);
			tx.commit();
			session.close();
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	public boolean updateProductCategory(List<ProductCategory> productCategoryList) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteProductCategory(Category category) {
		try {
			StringBuilder sqlCmd = new StringBuilder("Update ProductCategory pc set pc.dueDate =:nowDate");
			sqlCmd.append(" , pc.deleteFlg = :deleteFlg");
			sqlCmd.append(" WHERE pc.categoryCode = :categoryCode");
			Query query = sessionFactory.getCurrentSession().createQuery(sqlCmd.toString());
			query.setParameter("categoryCode", category.getCategoryCode());
			query.setParameter("deleteFlg", Constant.deleteFlg);
			query.setParameter("nowDate", Utils.getSqlDateNow());
			query.executeUpdate();

			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean deleteProductCategory(Category category, List<Product> productList) {
		// TODO Auto-generated method stub
		try {
			for (Product product : productList) {
				StringBuilder sqlCmd = new StringBuilder("Update ProductCategory pc set pc.dueDate =:nowDate");
				sqlCmd.append(" , pc.deleteFlg = :deleteFlg");
				sqlCmd.append(" WHERE pc.categoryCode = :categoryCode");
				sqlCmd.append(" AND pc.productCode = :productCode");

				Query query = sessionFactory.getCurrentSession().createQuery(sqlCmd.toString());
				query.setParameter("categoryCode", category.getCategoryCode());
				query.setParameter("deleteFlg", Constant.deleteFlg);
				query.setParameter("productCode", product.getProductCode());
				query.setParameter("nowDate", Utils.getSqlDateNow());
				query.executeUpdate();
			}
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	public boolean addProductCategory(Category category, List<Product> productList) {
		try {
			for (Product product : productList) {
				ProductCategory pc = new ProductCategory();
				pc.setCategoryCode(category.getCategoryCode());
				pc.setProductCode(product.getProductCode());
				pc.setStartDate(Utils.getSqlDateNow());
				pc.setDueDate(Utils.getMaxSqlDateNow());
				addProductCategory(pc);
			}
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

}
