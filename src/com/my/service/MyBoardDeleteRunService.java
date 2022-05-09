package com.my.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.controller.MyConstants;
import com.my.dao.MyBoardDao;

public class MyBoardDeleteRunService implements IMyBoardService{
	private Connection conn;
	private MyBoardDao myBoardDao;
	
	public MyBoardDeleteRunService(Connection conn) {
		this.conn = conn;
		myBoardDao = new MyBoardDao(conn);
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		String page = request.getParameter("page");
		System.out.println("b_no:" + b_no);
		boolean result = myBoardDao.deleteArticle(b_no);
		request.getSession().setAttribute("delete_result", result);
		return MyConstants.REDIRECT + "/mypro17/myboard/list?page=" + page;
	}

}
