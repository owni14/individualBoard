package com.my.test;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.controller.MyConstants;
import com.my.dao.MyBoardDao;
import com.my.service.IMyBoardService;
import com.my.vo.MyBoardVo;

public class MyBoardInsertData implements IMyBoardService{
	private Connection conn;
	private MyBoardDao myBoardDao;
	
	public MyBoardInsertData(Connection conn) {
		this.conn = conn;
		myBoardDao = new MyBoardDao(conn);
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		conn.setAutoCommit(false);
		for (int i = 1; i < 500; i++) {
			MyBoardVo vo = new MyBoardVo();
			vo.setB_title("제목" + i);
			vo.setB_content("내용" + i);
			vo.setId("lee");
			myBoardDao.insertArticle(vo);
		}
		conn.setAutoCommit(true);
		conn.commit();
		return MyConstants.REDIRECT + "/mypro17/myboard/list";
	}
	
}
