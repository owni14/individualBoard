package com.my.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.controller.MyConstants;
import com.my.dao.MyMemberDao;
import com.my.dao.MyBoardDao;
import com.my.vo.MyMemberVo;
import com.my.vo.MyBoardVo;

public class MyBoardReplyRunService implements IMyBoardService{
	private Connection conn;
	private MyBoardDao myBoardDao;
	private MyMemberDao memberDao;
	
	public MyBoardReplyRunService(Connection conn) {
		this.conn = conn;
		myBoardDao = new MyBoardDao(conn);
		memberDao = new MyMemberDao(conn);
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int g_no = Integer.parseInt(request.getParameter("g_no"));
		int re_seq = Integer.parseInt(request.getParameter("re_seq"));
		int re_level = Integer.parseInt(request.getParameter("re_level"));
		String b_title = request.getParameter("b_title");
		String b_content = request.getParameter("b_content");
		String id = request.getParameter("id");
		
		/**
		 * System.out.println("g_no:" + g_no);
		 * System.out.println("re_seq:" + re_seq);
		 * System.out.println("re_level:" + re_level);
		 * System.out.println("b_title:" + b_title);
		 * System.out.println("b_content:" + b_content);
		 * System.out.println("id:" + id);
		 */
		
		conn.setAutoCommit(false);
		MyBoardVo myBoardVo = new MyBoardVo(g_no, b_title, b_content, id, re_seq, re_level);
		boolean result = myBoardDao.updateContentAndInsertReply(myBoardVo);
		if (result) {
			conn.commit();
		} else {
			System.out.println("rollback");
			conn.rollback();
		}
		conn.setAutoCommit(true);
		request.getSession().setAttribute("reply_result", result);
		return MyConstants.REDIRECT + "/mypro17/myboard/list";
	}

}
