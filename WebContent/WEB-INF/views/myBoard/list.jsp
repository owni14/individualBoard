<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
<%@ include file="/WEB-INF/views/include/bootstrap_cdn.jsp"%>
<script>
$(function() {
	var insert_result = "${sessionScope.insert_result}";
	var delete_result = "${sessionScope.delete_result}";
	var reply_result  =  "${sessionScope.reply_result}";
	if (insert_result == "true") {
		alert("글 작성 완료");
	} else if (insert_result == "false") {
		alert("글 작성 오류");
	}
	if (delete_result == "true") {
		alert("글 삭제 완료");
	} else if (delete_result == "false") {
		alert("글 삭제 오류");
	}
	if (reply_result == "true") {
		alert("답글 등록 완료");
	} else if (reply_result == "false") {
		alert("답글 등록 오류");
	}
	
	$("a.page-link").click(function(e) {
		e.preventDefault();
		var href = $(this).attr("href");
// 		console.log("href:" + href);
		$("#frmPaging > input[name=page]").val(href);
		$("#frmPaging").submit();
	});
	
	$("#showRow").change(function() {
		var row = $(this).val();
// 		console.log("row:" + row);
		$("#frmPaging > input[name=showRow]").val(row);
		$("#frmPaging").submit();
	});
	
});
</script>
</head>
<%
	session.removeAttribute("insert_result");
	session.removeAttribute("delete_result");
	session.removeAttribute("reply_result");
%>
<body>
<%@ include file="/WEB-INF/views/include/paging_form.jsp" %>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron">
					<h1>
						<span>글목록</span>
					</h1>
					<h2>${sessionScope.myMemberVo.id}(${sessionScope.myMemberVo.name})님 반갑습니다. (<span style="font-weight: bold">현재포인트</span>:<a href="#">${sessionScope.myMemberVo.point}</a>점) <a href="/mypro17/mymember/logout_run" class="btn btn-danger">로그아웃</a></h2>
					<p>
						<a class="btn btn-primary btn-large"
							href="/mypro17/myboard/write_form">글쓰기</a>
					</p>
				</div>
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
					<form action="/mypro17/myboard/list" method="get">
						<select name="searchType">
							<option>선택
							<option>--------------------
							<option value="tit" 
								<c:if test="${param.searchType == 'tit'}">
									selected="selected"
								</c:if>
							>제목
							<option value="cont"
								<c:if test="${param.searchType == 'cont'}">
									selected="selected"
								</c:if>
							>내용
							<option value="wrt"
								<c:if test="${param.searchType == 'wrt'}">
									selected="selected"
								</c:if>
							>작성자
							<option value="tit+cont"
								<c:if test="${param.searchType == 'tit+cont'}">
									selected="selected"
								</c:if>
							>제목 + 내용
							<option value="tit+cont+wrt"
								<c:if test="${param.searchType == 'tit+cont+wrt'}">
									selected="selected"
								</c:if>
							>제목 + 내용 + 작성자
						</select>
						<input type="text" name="keyword" value="${param.keyword}">
						<button type="submit" class="btn btn-sm btn-dark">검색</button>
						
						<select id="showRow" name="showRow">
							<c:forEach var="v" begin="5" end="30" step="5">
								<option value="${v}" 
									<c:choose>
										<c:when test="${empty param.showRow && v == 10}">
											selected="selected"
										</c:when>
										<c:when test="${not empty param.showRow && param.showRow == v}">
											selected="selected"
										</c:when>
									</c:choose>
								>${v}줄씩 보기
							</c:forEach>
						</select>
						</form>
						<table class="table">
							<thead>
								<tr>
									<th>글번호</th>
									<th>글제목</th>
									<th>작성자</th>
									<th>작성일</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="myBoard">
									<tr>
										<td>${myBoard.b_no}</td>
										<td style="padding-left: ${myBoard.re_level * 40}px"><c:if
												test="${myBoard.re_level > 0}">ㄴ</c:if> <a
											href="/mypro17/myboard/content?b_no=${myBoard.b_no}&page=${pagingDto.page}">${myBoard.b_title}</a>
											<c:if test="${myBoard.read_count > 5}">
											<img alt="image" src="/mypro17/images/star.png">
											</c:if>
										</td>
										<td>${myBoard.id}</td>
										<td><fmt:formatDate value="${myBoard.b_date}"
												pattern="YYYY-MM-YY HH:mm:ss" /></td>
										<td>${myBoard.read_count}</td>
									</tr>
								</c:forEach>
							</tbody>

						</table>
					</div>
					<div class="col-md-2"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<nav>
					<ul class="pagination justify-content-center">
						<c:if test="${pagingDto.startPage != 1}">
							<li class="page-item"><a class="page-link" href="${pagingDto.startPage - 1}">Previous</a></li>
						</c:if>
						<c:forEach var="v" begin="${pagingDto.startPage}" end="${pagingDto.endPage}">
							<li 
								<c:choose>
									<c:when test="${pagingDto.page == v}">
										class="page-item active"
									</c:when>
									<c:otherwise>
										class="page-item"
									</c:otherwise>
								</c:choose>
							><a class="page-link" href="${v}">${v}</a></li>
						</c:forEach>
						<c:if test="${pagingDto.endPage != pagingDto.totalPage}">
							<li class="page-item"><a class="page-link" href="${pagingDto.endPage + 1}">Next</a>
						</c:if>
						</li>
					</ul>
				</nav>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
</body>
</html>