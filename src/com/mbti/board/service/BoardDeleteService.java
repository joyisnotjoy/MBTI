package com.mbti.board.service;

import com.mbti.board.dao.BoardDAO;
import com.mbti.main.controller.Service;

public class BoardDeleteService implements Service{
	//dao가 필요하다. 밖에서 생성한 후 넣어준다. - 1. 생성자. 2. setter()
	private BoardDAO dao;
	
	// 기본 생성자 만들기 -> 확인 시 필요
	public BoardDeleteService() {
		System.out.println("BoardDeleteService() - 생성 완료");
	} 
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 넘어오는 데이터 확인
		System.out.println("BoardDeleteService.obj : " + obj);
		return dao.delete((Long) obj);
	}

	@Override
	public void setDAO(Object dao) {
		// TODO Auto-generated method stub
		System.out.println("BoardDeleteService.setDAO().dao : " + dao);
		this.dao = (BoardDAO) dao;
	}
	

}
