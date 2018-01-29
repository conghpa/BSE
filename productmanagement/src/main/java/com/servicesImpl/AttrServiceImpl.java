package com.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AttrDao;
import com.entities.Attribute;
import com.services.AttrService;

@Service
public class AttrServiceImpl implements AttrService{
	@Autowired 
	AttrDao attrDao;

	public List<Attribute> getAllProduct() {
		// TODO Auto-generated method stub
		return attrDao.getAllAttr();
	}

	public boolean deleteAttr(Attribute attr) {
		// TODO Auto-generated method stub
		return attrDao.deleteAttr(attr);
	}

	public boolean udpateAttr(Attribute attr) {
		// TODO Auto-generated method stub
		return attrDao.udpateAttr(attr);
	}
	
	
}
