package com.mbti.list.service;

import com.mbti.list.dao.ListDAO;
import com.mbti.list.vo.ListVO;
import com.mbti.main.controller.Service;
import com.mbti.util.page.PageObject;

public class ListUpdateService implements Service {

	ListDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		
		return dao.register((ListVO) obj);
		   
	}

	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub
		
		this.dao = (ListDAO) dao;

	}

}
