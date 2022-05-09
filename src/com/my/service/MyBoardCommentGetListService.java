package com.my.service;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.my.controller.MyConstants;
import com.my.dao.MyCommentDao;
import com.my.vo.MyCommentVo;

public class MyBoardCommentGetListService implements IMyBoardService{
	private Connection conn;
	private MyCommentDao myCommentDao;
	
	public MyBoardCommentGetListService(Connection conn) {
		this.conn = conn;
		myCommentDao = new MyCommentDao(conn);
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int b_no = Integer.parseInt(request.getParameter("b_no"));
		List<MyCommentVo> myCommentVoList = myCommentDao.getCommentList(b_no);
		
		/** content.jsp에서 $.post로 넘어온값(b_no)확인 및 getCommentList로 받아온 list데이터 확인
		 * System.out.println("b_no:" + b_no);
		 * System.out.println("myCommentVoList" + myCommentVoList);
		 */
		JSONArray jArray = new JSONArray();
		for (MyCommentVo vo : myCommentVoList) {
			JSONObject jObject = new JSONObject();
			jObject.put("c_id", vo.getC_id());
			jObject.put("c_content", vo.getC_content());
			jObject.put("id", vo.getId());
			jObject.put("c_date", vo.getC_date().toString());
			jArray.add(jObject);
		}
		
		request.setAttribute("data", jArray.toJSONString());
		return MyConstants.SEND_DATA;
	}
	
}
