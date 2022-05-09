package com.my.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.controller.MyConstants;
import com.my.dao.MyMemberDao;

public class MyDeleteService implements IMemberService{
	private Connection conn;
	private MyMemberDao dao;
	
	public MyDeleteService(Connection conn) {
		this.conn = conn;
		dao = new MyMemberDao(conn);
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		boolean result = dao.delMember(id);
		request.getSession().setAttribute("delete_result", result);
		return MyConstants.REDIRECT + "/mypro17/mymember/list";
	}
	
}
