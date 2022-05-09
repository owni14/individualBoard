package com.my.controller;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.MyConnectionManager;
import com.my.service.IMyBoardService;
import com.my.service.MyBoardCommentDeleteRunService;
import com.my.service.MyBoardCommentGetListService;
import com.my.service.MyBoardCommentUpdateRunService;
import com.my.service.MyBoardCommentWriteRunService;
import com.my.service.MyBoardContentService;
import com.my.service.MyBoardDeleteRunService;
import com.my.service.MyBoardListService;
import com.my.service.MyBoardModifyRunService;
import com.my.service.MyBoardReplyRunService;
import com.my.service.MyBoardReplyService;
import com.my.service.MyBoardWriteRunService;
import com.my.service.MyBoardWriteService;
import com.my.test.MyBoardInsertData;

@WebServlet("/myboard/*")
public class MyBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private com.my.service.IMyBoardService service; 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		Connection conn = MyConnectionManager.getConnection();
		
		try {
			switch (path) {
			case "/list":
				service = new MyBoardListService(conn);
				break;
			case "/content":
				service = new MyBoardContentService(conn);
				break;
			case "/write_form":
				service = new MyBoardWriteService();
				break;
			case "/write_run":
				service = new MyBoardWriteRunService(conn);
				break;
			case "/reply_form":
				service = new MyBoardReplyService();
				break;
			case "/reply_run":
				service = new MyBoardReplyRunService(conn);
				break;
			case "/modify_run":
				service = new MyBoardModifyRunService(conn);
				break;
			case "/delete_run":
				service = new MyBoardDeleteRunService(conn);
				break;
			case "/insertTestData":
				service = new MyBoardInsertData(conn);
				break;
			case "/comment_write_run":
				service = new MyBoardCommentWriteRunService(conn);
				break;
			case "/comment_get_list":
				service = new MyBoardCommentGetListService(conn);
				break;
			case "/comment_update":
				service = new MyBoardCommentUpdateRunService(conn);
				break;
			case "/comment_delete":
				service = new MyBoardCommentDeleteRunService(conn);
				break;
			}
		
			String page = service.execute(request, response);
			if (page.startsWith(MyConstants.REDIRECT)) {
				response.sendRedirect(page.substring(MyConstants.REDIRECT.length()));
			} else if (page.equals(MyConstants.SEND_DATA)) {
				response.setCharacterEncoding("utf-8");
				String data = (String) request.getAttribute("data");
				response.getWriter().print(data);
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
		request.getSession().setAttribute("lastCommand", path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
