package com.services;

import java.util.List;
import com.entities.Attribute;

public interface AttrService {
	public List<Attribute> getAllProduct();
	public boolean deleteAttr(Attribute attr);
	public boolean udpateAttr(Attribute attr);
}
