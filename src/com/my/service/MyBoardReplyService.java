package com.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyBoardReplyService implements IMyBoardService{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "myBoard/reply_form";
	}

}
