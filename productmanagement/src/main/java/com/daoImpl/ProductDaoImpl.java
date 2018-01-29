package com.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.common.Constant;
import com.dao.ProductDao;
import com.entities.Product;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao{

	@Autowired
	SessionFactory session;
	
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Product Where deleteFlg <> '" + Constant.deleteFlg + "'").list();
	}

	public Product getProduct(Product product){
		return (Product)session.getCurrentSession().createQuery("From Product Where productCode = '" + product.getProductCode() + "'").list().get(0);
	}
	
	public boolean deleteProduct(Product product) {
		// TODO Auto-generated method stub
		try {
			product = getProduct(product);
			//1: deleted state
			product.setDeleteFlg(1);
			saveOrUdpateProduct(product);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public boolean saveOrUdpateProduct(Product product) {
		// TODO Auto-generated method stub
		try {
			session.getCurrentSession().saveOrUpdate(product);
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}

	public List<Product> getAllProduct(Product product) {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Product p where p.productCode = " + product.getProductCode()).list();
	}
	
}
