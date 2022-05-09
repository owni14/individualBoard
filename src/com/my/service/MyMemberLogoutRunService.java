package com.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.controller.MyConstants;

public class MyMemberLogoutRunService implements IMemberService{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().invalidate();
		request.getSession().setAttribute("logout_result_success", "success");
		return MyConstants.REDIRECT + "/mypro17/mymember/login_form";
	}

}
