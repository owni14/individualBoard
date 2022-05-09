<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listMembers.jsp</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var join_result = "${sessionScope.join_result}";
		var modify_result = "${sessionScope.modify_result}";
		var delete_result = "${sessionScope.delete_result}";

		if (join_result == "true") {
			alert("회원 가입 완료");
		} else if (join_result == "false") {
			alert("회원 가입 실패");
		}

		if (modify_result == "true") {
			alert("회원 정보 수정 완료");
		} else if (modify_result == "false") {
			alert("회원 정보 수정 오류");
		}

		if (delete_result == "true") {
			alert("회원 삭제 완료");
		} else if (delete_result == "false") {
			alert("회원 삭제 실패");
		}

		var isChecked = false;
		$("#chkSelect").click(function() {
			isChecked = !isChecked;
			console.log(isChecked);
			$(".checkSel").attr("checked", isChecked);
		});

	});
</script>
</head>
<body>
	<form method="post" action="/mypro17/mymember/delete_selected">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<a href="/mypro17/mymember/join_form" class="btn btn-success">회원가입</a>
					<button type="submit" class="btn btn-danger">선택한 회원 모두 삭제</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<table class="table">
						<thead>
							<tr>
								<th><input type="checkbox" name="chkAll" id="chkSelect"></th>
								<th>아이디</th>
								<th>비밀번호</th>
								<th>이름</th>
								<th>이메일</th>
								<th>가입일</th>
								<th>수정</th>
								<th>삭제</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="vo" varStatus="status">
								<tr
									<c:if test="${status.index % 2 == 1}">
									class="table-active"
								</c:if>>
									<td><input type="checkbox" class="checkSel" name="delId"
										value="${vo.id}"></td>
									<td>${vo.id}</td>
									<td>${vo.pwd}</td>
									<td>${vo.name}</td>
									<td>${vo.email}</td>
									<td>${vo.joindate}</td>
									<td><a href="/mypro17/mymember/modify_form?id=${vo.id}"
										class="btn btn-sm btn-warning">수정</a></td>
									<td><a href="/mypro17/mymember/delete?id=${vo.id}"
										class="btn btn-sm btn-danger">삭제</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="col-md-2"></div>
			</div>
		</div>
	</form>
</body>
</html>
<%
	session.removeAttribute("modify_result");
	session.removeAttribute("join_result");
	session.removeAttribute("delete_result");
%>