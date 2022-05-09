package com.my.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.controller.MyConstants;
import com.my.dao.MyMemberDao;

public class MyDeleteSelectedService implements IMemberService{
	private Connection conn;
	private MyMemberDao dao;
	
	public MyDeleteSelectedService(Connection conn) {
		this.conn = conn;
		dao = new MyMemberDao(conn);
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] delIds = request.getParameterValues("delId");
	
		/** 
		* for (String id : delIds) {
		* 	System.out.println("id:" + id);
		* }
		*/
		
		dao.delMember(delIds);
		return MyConstants.REDIRECT + "/mypro17/mymember/list";
	}

}
