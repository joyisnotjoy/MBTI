package com.mbti.hit.service;

import com.mbti.list.dao.ListDAO;
import com.mbti.main.controller.Service;

public class MBTIHitService implements Service {

	private ListDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		return dao.increase((long) obj);
		
	}

	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub

		this.dao = (ListDAO) dao;
		
	}

}
