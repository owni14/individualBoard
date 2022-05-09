<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>reply_form.jsp</title>
<%@ include file="/WEB-INF/views/include/bootstrap_cdn.jsp" %>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron card card-block">
					<h2>댓글쓰기</h2>
					<div>아래 항목을 빠짐 없이 작성해주세요.</div>
					<p>
						<a class="btn btn-primary btn-large" href="/mypro17/myboard/list">목록</a>
					</p>
				</div>
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<form role="form" action="/mypro17/myboard/reply_run" method="post">
						<input type="hidden" name="g_no" value="${param.g_no}">
						<input type="hidden" name="re_seq" value="${param.re_seq}">
						<input type="hidden" name="re_level" value="${param.re_level}">
							<div class="form-group">
								<label for="b_title"> 답글제목 </label> <input type="text"
									class="form-control" name="b_title" value="[Re]"/>
							</div>
							<div class="form-group">
								<label for="b_content"> 답글내용 </label> <input type="text"
									class="form-control" name="b_content" />
							</div>
							<div class="form-group">
								<label for="exampleInputFile"> 첨부파일 </label> <input type="file"
									class="form-control-file" id="exampleInputFile" />
							</div>
							<div class="form-group">
								<label for="id"> 작성자 </label> <input type="text"
									class="form-control" name="id" />
							</div>
							<button type="submit" class="btn btn-primary">확인</button>
						</form>
					</div>
					<div class="col-md-2"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>