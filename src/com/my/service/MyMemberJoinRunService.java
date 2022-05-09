package com.my.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.controller.MyConstants;
import com.my.dao.MyMemberDao;
import com.my.vo.MyMemberVo;

public class MyMemberJoinRunService implements IMemberService{
	private Connection conn;
	private MyMemberDao dao;
	
	public MyMemberJoinRunService(Connection conn) {
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
		boolean result = dao.addMember(vo);
		request.getSession().setAttribute("join_result", result);
		
		// 회원 가입 처리 후에 목록으로 이동
		return MyConstants.REDIRECT + "/mypro17/mymember/list"; // redirect를 안하게 되면 forward되기 때문에 redirect를 적어준다
		// -> /WEB-INF/views/redirect:test01/listMembers.jsp
	}

}
