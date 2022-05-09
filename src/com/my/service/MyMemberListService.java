package com.my.service;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.MyMemberDao;
import com.my.vo.MyMemberVo;

public class MyMemberListService implements IMemberService{
	private Connection conn;
	private MyMemberDao dao;
	
	public MyMemberListService(Connection conn) {
		this.conn = conn;
		dao = new MyMemberDao(conn);
	}
	
	// IMemberService 의 추상 메서드
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<MyMemberVo> list = dao.listMembers();
		request.setAttribute("list", list);
		return "myMember/listMembers";
	}
	
}