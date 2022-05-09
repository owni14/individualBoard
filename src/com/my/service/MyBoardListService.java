package com.my.service;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.MyBoardDao;
import com.my.vo.MyBoardVo;
import com.my.vo.MyPagingDto;

public class MyBoardListService implements IMyBoardService{
	private Connection conn;
	private MyBoardDao myBoardDao;
	
	public MyBoardListService(Connection conn) {
		this.conn = conn;
		myBoardDao = new MyBoardDao(conn);
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MyPagingDto pagingDto = new MyPagingDto();
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		String showRow = request.getParameter("showRow");
		
		if (showRow != null && !(showRow == "")) {
			pagingDto.setShowRow(Integer.parseInt(showRow));
		}
		
		pagingDto.setSearchType(searchType);
		pagingDto.setKeyword(keyword);
		
		/** 넘어오는 값 출력 확인
		 * System.out.println("searchType:" + searchType);
		 * System.out.println("keyword:" + keyword);
		 * System.out.println("pagingDto:" + pagingDto);
		 * System.out.println("showRow:" + showRow);
		 */
		
		// page없이  list만 넘어왔을 경우 기본 page는 1로 설정 
		int page = 1;
		String strPage = request.getParameter("page");
		if (strPage != null && !(strPage.equals(""))) {
			page = Integer.parseInt(strPage);
		}
		
		int totalCount = myBoardDao.getTotalCount(pagingDto);
		pagingDto.setPage(page, totalCount);
		
		List<MyBoardVo> list = myBoardDao.getList(pagingDto);
		request.setAttribute("list", list);
		request.setAttribute("pagingDto", pagingDto);
		return "myBoard/list";
	}

}
