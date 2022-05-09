<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login_form.jsp</title>
<%@ include file="/WEB-INF/views/include/bootstrap_cdn.jsp" %>
<script>
$(function() {
	var success = "${sessionScope.logout_result_success}"
	if (success != null &&  !(success == "")) {
		alert("로그아웃 성공");
	}
});
</script>
</head>
<body>
<%
	session.removeAttribute("logout_result_success");
%>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron">
					<h2>로그인</h2>
					<p>
						<a class="btn btn-primary btn-large" href="/mypro17/mymember/join_form">회원가입</a>
					</p>
				</div>
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<form role="form" action="/mypro17/mymember/login_form_run" method="post">
							<div class="form-group">
								<label for="id"> 아이디 </label> 
								<input type="text" class="form-control" name="id" value="${cookie.id.value}"/>
							</div>
							<div class="form-group">
								<label for="pw"> 비밀번호 </label> 
								<input type="password" class="form-control" name="pwd" />
							</div>
							<div class="checkbox">
								<label> <input type="checkbox" name="saveId" value="checked" checked/> 아이디 저장
								</label>
							</div>
							<button type="submit" class="btn btn-primary">로그인</button>
						</form>
					</div>
					<div class="col-md-2"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>