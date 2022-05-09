package com.my.service;


import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.MyBoardDao;
import com.my.vo.MyBoardVo;

public class MyBoardContentService implements IMyBoardService{
	private Connection conn;
	private MyBoardDao myBoardDao;
	
	public MyBoardContentService(Connection conn) {
		this.conn = conn;
		myBoardDao = new MyBoardDao(conn);
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		String page = request.getParameter("page");
		String lastCommand = (String)request.getSession().getAttribute("lastCommand");
		String currentPath = request.getPathInfo();
		
		/** 이전페이지와 현재 페이지 출력 확인
		* System.out.println("lastCommand:" + lastCommand);
		* System.out.println("currentPath:" + currentPath);
		*/
		
		if (lastCommand == null || !(lastCommand.equals(currentPath))) {
			myBoardDao.increaseCount(b_no);
		}
		MyBoardVo myBoardVo = myBoardDao.getArticleByBno(b_no);
		request.setAttribute("myBoardVo", myBoardVo);
		request.setAttribute("page", page);
//		System.out.println("b_no:" + b_no);
		return "myBoard/content";
	}

}
