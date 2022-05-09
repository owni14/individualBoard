package com.my.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.controller.MyConstants;
import com.my.dao.MyBoardDao;
import com.my.vo.MyBoardVo;


public class MyBoardModifyRunService implements IMyBoardService{
	private Connection conn;
	private MyBoardDao myBoardDao;
	
	public MyBoardModifyRunService(Connection conn) {
		this.conn = conn;
		myBoardDao = new MyBoardDao(conn);
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		String page = request.getParameter("page");
		String b_title = request.getParameter("b_title");
		String b_content = request.getParameter("b_content");
		MyBoardVo myBoardVo = new MyBoardVo(b_no, b_title, b_content); 
		boolean result = myBoardDao.updateArticle(myBoardVo);
		request.getSession().setAttribute("update_result", result);
		
		/** 넘어온 데이터 출력 테스트
		 * System.out.println("b_title:" + b_title);
		 * System.out.println("b_content:" + b_content);
		 */
		
		return MyConstants.REDIRECT + "/mypro17/myboard/content?b_no=" + b_no +"&page=" + page;
	}

}
