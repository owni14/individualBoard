package com.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IMemberService {
	// String값은 서비스 처리 후 보여줄 JSP Page를 의미
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}