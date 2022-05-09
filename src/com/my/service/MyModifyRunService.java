package com.my.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.controller.MyConstants;
import com.my.dao.MyMemberDao;
import com.my.vo.MyMemberVo;

public class MyModifyRunService implements IMemberService{
	private Connection conn;
	private MyMemberDao dao;	
	
	public MyModifyRunService(Connection conn) {
		this.conn = conn;
		dao = new MyMemberDao(conn);
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		MyMemberVo vo = new MyMemberVo(id, pwd, name, email);
		boolean result = dao.updateMember(vo);
		HttpSession session = request.getSession();
		session.setAttribute("modify_result", result);
		return MyConstants.REDIRECT + "/mypro17/mymember/list";
	}

}
