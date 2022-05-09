package com.my.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.MyConnectionManager;
import com.my.service.MyDeleteSelectedService;
import com.my.service.MyDeleteService;
import com.my.service.IMemberService;
import com.my.service.MyMemberJoinFormService;
import com.my.service.MyMemberJoinRunService;
import com.my.service.MyMemberListService;
import com.my.service.MyMemberLoginFormRunService;
import com.my.service.MyMemberLoginFormService;
import com.my.service.MyMemberLogoutRunService;
import com.my.service.MyModifyFormService;
import com.my.service.MyModifyRunService;

@WebServlet("/mymember/*")
public class MyMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private MemberDao dao = MemberDao.getInstance();
//	 MemberListService is a IMemberService
	private IMemberService service;
	
	/**
	 * 페이지 접두어
	 * private final String PREFIX = "/WEB-INF/views/myMember/";
	 * 페이지 접미어
	 * public final String POSTFIX = ".jsp";
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * List<MemberVo> list = dao.listMembers();
		 * request.setAttribute("list", list);
		 * String command = getCommand(request);
		*/
		String command = request.getPathInfo();
		
		Connection conn = MyConnectionManager.getConnection();
		
		try {
			switch (command) {
			case "/list": // 목록
				service = new MyMemberListService(conn);
				break;
			case "/join_form": // 가입양식
				service = new MyMemberJoinFormService();
				break;
			case "/join_run": // 가입처리
				service = new MyMemberJoinRunService(conn);
				break;
			case "/modify_form": // 수정양식
				service = new MyModifyFormService(conn);
				break;
			case "/modify_run": // 수정처리
				service = new MyModifyRunService(conn);
				break;
			case "/delete": // 삭제처리
				service = new MyDeleteService(conn);
				break;
			case "/delete_selected": // 체크된거 삭제처리
				service = new MyDeleteSelectedService(conn);
				break;
			case "/login_form":
				service = new MyMemberLoginFormService();
				break;
			case "/login_form_run":
				service = new MyMemberLoginFormRunService(conn);
				break;
			case "/logout_run":
				service = new MyMemberLogoutRunService();
				break;
			}
			String page = service.execute(request, response);
			// JoinRunService는 redirect가 붙여서 나온다.
			// redirect: 으로 시작하면 리다이레트 그렇지않으면 forward
			if (page.startsWith(MyConstants.REDIRECT)) {
				page = page.substring(MyConstants.REDIRECT.length());
				response.sendRedirect(page);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(MyConstants.PREFIX + page + MyConstants.POSTFIX);
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/** uri에서 command를 찾아서 반환해주는 method
	 * @SuppressWarnings("unused")
	 * private String getCommand(HttpServletRequest request) {
	 * String uri = request.getRequestURI();
	 * System.out.println("uri:" + uri);
	 ** / 의 인덱스 번호 얻기
	 * int slashIndex = uri.lastIndexOf("/");
	 * System.out.println("slashIndex:" + slashIndex);
	 * String command = uri.substring(slashIndex + 1);
	 * System.out.println("command:" + command);
	 * return command;
	 * }
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}