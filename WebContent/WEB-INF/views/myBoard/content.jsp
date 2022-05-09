<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>content.jsp</title>
<%@ include file="/WEB-INF/views/include/bootstrap_cdn.jsp"%>
<script>
$(function () {
	var update_result = "${sessionScope.update_result}";
	if (update_result == "true") {
		alert("글 수정 완료");
	} else if (update_result == "false") {
		alert("글 수정 오류");
	}
	
	// 수정버튼을 누르면 숨어있던 수정확인버튼 나오게 하는 함수
	$(function() {
		$("#modifyReadyStatus").click(function(e) {
			e.preventDefault();
			$("*[readonly]").removeAttr("readonly");
			$("#modifyReadyStatus").fadeOut("slow");
			$("#modify").fadeIn("slow");
		});
	});
	
	$("#btnComment").click(function() {
		var content = $("#cmtContent").val();
// 		console.log("commentContent:" + content);
		url = "/mypro17/myboard/comment_write_run";
		sendData = {
				"content" : content,
				"b_no" 	  : "${myBoardVo.b_no}"
		};
		$.post(url, sendData, function(receivedData) {
// 			console.log("receivedData: " + receivedData);
			if (receivedData == "true") {
				$("#cmtContent").val("");
				$("#commentTable > tbody > tr").remove();
				getCommentList();
			} else if (receivedData == "false") {
				alert("답글 입력 오류");
			}
		});
	});
	
	getCommentList()
	
	function getCommentList() {
// 			console.log("executed");	
		var url = "/mypro17/myboard/comment_get_list";
		var sendData = {
				"b_no" : "${myBoardVo.b_no}"
		};
		$.post(url, sendData, function(receivedData) {
// 			console.log("receivedData:" + receivedData);
			var jData = JSON.parse(receivedData);
// 			console.log("jData:" + jData);
			$.each(jData, function() {
// 				console.log(this);
				var tr = "<tr>";
				tr += "<td>" + this.c_id + "</td>";
				tr += "<td>" + this.c_content + "</td>";
				tr += "<td>" + this.id + "</td>";
				tr += "<td>" + this.c_date + "</td>";
				tr += "</tr>";
				$("#commentTable > tbody").append(tr);
			});
		});
	}
});
</script>
</head>
<%
	session.removeAttribute("update_result");
%>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron card card-block">
					<h2>글 내용 보기</h2>
					<p>
						<a class="btn btn-primary btn-large"
							href="/mypro17/myboard/list?page=${page}">글목록</a>
					</p>
				</div>
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<form
							action="/mypro17/myboard/modify_run?b_no=${myBoardVo.b_no}&page=${page}"
							method="post">
							<table class="table">
								<tbody>
									<tr>
										<th>글번호</th>
										<td>${myBoardVo.b_no}</td>
									</tr>
									<tr>
										<th>글제목</th>
										<td><input type="text" value="${myBoardVo.b_title}"
											class="form-control" id="b_title" name="b_title" readonly></td>
									</tr>
									<tr>
										<th>글내용</th>
										<td><textarea class="form-control" id="b_content"
												name="b_content" readonly>${myBoardVo.b_content}</textarea></td>
									</tr>
									<tr>
										<th>작성자</th>
										<td>${myBoardVo.id}</td>
									</tr>
									<tr>
										<th>작성일</th>
										<td><fmt:formatDate value="${myBoardVo.b_date}"
												pattern="YYYY-MM-dd HH:mm:ss" /></td>
									</tr>
									<tr>
										<th>조회수</th>
										<td>${myBoardVo.read_count}</td>
									</tr>
									<tr>
										<td colspan="2" style="text-align: center">
										<a href="#" class="btn btn-warning" id="modifyReadyStatus">수정</a>
										<button class="btn btn-success" style="display: none;" id="modify">수정완료</button> 
										<a href="/mypro17/myboard/delete_run?b_no=${myBoardVo.b_no}&page=${page}" class="btn btn-danger">삭제</a>
										<a href="/mypro17/myboard/reply_form?g_no=${myBoardVo.g_no}&re_seq=${myBoardVo.re_seq}&re_level=${myBoardVo.re_level}" class="btn btn-primary">답글</a></td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
					<div class="col-md-2"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<p style="text-align: center;">
					<span style="font-weight: bold">댓글</span>
				    <input type="text" id="cmtContent">
					<button class="btn btn-sm btn-dark" id="btnComment">확인</button>
				</p>
			</div>
			<div class="col-md-2"></div>
		</div>
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<table class="table" id="commentTable">
					<thead>
						<tr>
							<th>#</th>
							<th>Content</th>
							<th>Writer</th>
							<th>Date</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
</body>
</html>