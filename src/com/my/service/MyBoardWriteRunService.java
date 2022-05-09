package com.my.service;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.controller.MyConstants;
import com.my.dao.MyBoardDao;
import com.my.dao.MyPointDao;
import com.my.vo.MyBoardVo;
import com.my.vo.MyMemberVo;
import com.my.vo.MyPointVo;

public class MyBoardWriteRunService implements IMyBoardService{
	private Connection conn;
	private MyBoardDao myBoardDao;
	private MyPointDao myPointDao;
	
	public MyBoardWriteRunService(Connection conn) {
		this.conn = conn;
		myBoardDao = new MyBoardDao(conn);
		myPointDao = new MyPointDao(conn);
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String b_title = request.getParameter("b_title");
		String b_content = request.getParameter("b_content");
		MyMemberVo myMemberVo = (MyMemberVo) request.getSession().getAttribute("myMemberVo");
		String id = myMemberVo.getId();
		MyBoardVo vo = new MyBoardVo(b_title, b_content, id);
		boolean insert_result = myBoardDao.insertArticle(vo);
		if (insert_result) {
			int point = myPointDao.getPoint(MyConstants.POINT_CODE_WRITE_BOARD);
			boolean update_result = myBoardDao.updatePoint(id, point);
			MyPointVo myPointVo = new MyPointVo(id, MyConstants.POINT_CODE_WRITE_BOARD);
			boolean point_result = myPointDao.addPoint(myPointVo);
			if (update_result && point_result) {
				System.out.println("completed");
			}
		}
		request.getSession().setAttribute("insert_result", insert_result);
		
		/** 입력된값 가져오는지 확인
		 * System.out.println("b_title:" + b_title);
		 * System.out.println("b_content:" + b_content);
		 * System.out.println("id:" + id);
		 */
		
		return MyConstants.REDIRECT + "/mypro17/myboard/list";
	}
	
}
