package com.my.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.controller.MyConstants;
import com.my.dao.MyCommentDao;

public class MyBoardCommentDeleteRunService implements IMyBoardService{
	private Connection conn;
	private MyCommentDao myCommentDao;
	
	public MyBoardCommentDeleteRunService(Connection conn) {
		this.conn = conn;
		myCommentDao = new MyCommentDao(conn);
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int c_id = Integer.parseInt(request.getParameter("c_id"));
//		System.out.println("c_id:" + c_id);
		boolean result = myCommentDao.deleteComment(c_id);
		request.setAttribute("data", String.valueOf(result));
		return MyConstants.SEND_DATA;
	}
	
}
