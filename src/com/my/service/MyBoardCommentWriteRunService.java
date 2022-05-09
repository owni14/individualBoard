package com.my.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.controller.MyConstants;
import com.my.dao.MyCommentDao;
import com.my.vo.MyCommentVo;
import com.my.vo.MyMemberVo;

public class MyBoardCommentWriteRunService implements IMyBoardService{
	private Connection conn;
	private MyCommentDao myCommentDao;
	
	public MyBoardCommentWriteRunService(Connection conn) {
		this.conn = conn;
		myCommentDao = new MyCommentDao(conn);
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String c_content = request.getParameter("content");
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		MyMemberVo myMemberVo = (MyMemberVo) request.getSession().getAttribute("myMemberVo");
		String id = myMemberVo.getId();
		MyCommentVo myCommentVo = new MyCommentVo(c_content, id, b_no);
		boolean result = myCommentDao.insertComment(myCommentVo);
		
		/** content.jsp에서 $.post로 넘어온 값 확인
		 * System.out.println("c_content:" + c_content);
		 * System.out.println("b_no:" + b_no);
		 * System.out.println("id:" + id);
		 */
		
		request.setAttribute("data", String.valueOf(result));
		return MyConstants.SEND_DATA;
	}

}
