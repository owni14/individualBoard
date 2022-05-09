package com.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyMemberLoginFormService implements IMemberService{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "myMember/login_form";
	}

}
