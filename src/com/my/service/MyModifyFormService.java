package com.my.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.MyMemberDao;
import com.my.vo.MyMemberVo;

public class MyModifyFormService implements IMemberService{
	private Connection conn;
	private MyMemberDao dao;
	
	public MyModifyFormService(Connection conn) {
		this.conn = conn;
		dao = new MyMemberDao(conn);
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		MyMemberVo vo = dao.selectMemberById(id);
		request.setAttribute("vo", vo);
		return "myMember/memberModifyForm";
	}

}
