package com.my.service;

import java.sql.Connection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.controller.MyConstants;
import com.my.dao.MyMemberDao;
import com.my.vo.MyMemberVo;

public class MyMemberLoginFormRunService implements IMemberService{
	private Connection conn;
	private MyMemberDao myMemberDao;
	private MyMemberVo myMemberVo;
	
	public MyMemberLoginFormRunService(Connection conn) {
		this.conn = conn;
		myMemberDao = new MyMemberDao(conn);
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String saveId = request.getParameter("saveId");
		
		myMemberVo = new MyMemberVo(id, pwd);
		boolean result = myMemberDao.isExisted(myMemberVo);
		
		String page = "";
		if (result) {
			page = MyConstants.REDIRECT + "/mypro17/myboard/list";
			myMemberVo = myMemberDao.selectMemberById(id);
			request.getSession().setAttribute("myMemberVo", myMemberVo);
		} else {
			page = MyConstants.REDIRECT + "/mypro17/mymember/login_form";
		}
			
		Cookie cookie = new Cookie("id", id);
		cookie.setPath("/mypro17");
		if (saveId == null) {
			cookie.setMaxAge(0);
		} else {
			cookie.setMaxAge(60 * 60 * 24 * 7);
		}
		response.addCookie(cookie);
		
		/**
		 * System.out.println("id:" + id);
		 * System.out.println("pw:" + pwd);
		 * System.out.println("result:" + result);
		 * System.out.println("myMemberVo: " + myMemberVo);
		 * System.out.println("saveId:" + saveId);
		 */
		
		return page;
		
	}

}
