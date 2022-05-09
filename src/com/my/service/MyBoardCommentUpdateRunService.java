package com.my.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.controller.MyConstants;
import com.my.dao.MyCommentDao;

public class MyBoardCommentUpdateRunService implements IMyBoardService{
	private Connection conn;
	private MyCommentDao myCommentDao;
	
	public MyBoardCommentUpdateRunService(Connection conn) {
		this.conn = conn;
		myCommentDao = new MyCommentDao(conn);
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int c_id = Integer.parseInt(request.getParameter("c_id"));
		String c_content = request.getParameter("c_content");
		/*
		 * System.out.println("c_id:" + c_id); 
		 * System.out.println("c_content:" + c_content); 
		*/
		boolean result = myCommentDao.updateComment(c_id, c_content);
		request.setAttribute("data", String.valueOf(result));
		return MyConstants.SEND_DATA;
	}

}
