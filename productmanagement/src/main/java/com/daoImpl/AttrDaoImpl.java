package com.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AttrDao;
import com.entities.Attribute;

@Repository
@Transactional
public class AttrDaoImpl implements AttrDao{

	@Autowired
	SessionFactory session;
	
	public List<Attribute> getAllAttr() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Attribute").list();
	}

	public boolean deleteAttr(Attribute attr) {
		// TODO Auto-generated method stub
		try {
			//1: is deleted state
			attr.setDeleteFlg(1);
			session.getCurrentSession().update(attr);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public boolean udpateAttr(Attribute attr) {
		// TODO Auto-generated method stub
		try {
			session.getCurrentSession().update(attr);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

}
