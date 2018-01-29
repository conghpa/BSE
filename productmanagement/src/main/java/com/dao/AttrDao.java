package com.dao;

import java.util.List;

import com.entities.Attribute;


public interface AttrDao {
	public List<Attribute> getAllAttr();
	public boolean deleteAttr(Attribute attr);
	public boolean udpateAttr(Attribute attr);
}
