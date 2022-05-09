<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>memberForm.jsp</title>
</head>
<body>
	<div class="container-fluid">
		<div class="col-md-12">
			<div class="jumbotron">
				<h2>회원 수정 양식</h2>
				<p>아래 항목을 빠짐없이 작성해주세요.</p>
				<p>
					<a class="btn btn-primary btn-large" href="/mypro17/mymember/list">회원목록</a>
				</p>

			</div>
			<div class="row">
				<div class="col-md-12">
					<form role="form" action="/mypro17/mymember/modify_run" method="post">
						<div class="form-group">
							<label for="id"> 아이디 </label> <input type="text"
								class="form-control" id="id" name="id" value="${vo.id}" readonly/>
						</div>
						<div class="form-group">
							<label for="pwd"> 패스워드 </label> <input type="password"
								class="form-control" id="pwd" name="pwd" value="${vo.pwd}"/>
						</div>
						<div class="form-group">
							<label for="name"> 이름 </label> <input type="text"
								class="form-control" id="name" name="name" value="${vo.name}"/>
						</div>
						<div class="form-group">
							<label for="email"> 이메일 </label> <input type="email"
								class="form-control" id="email" name="email" value="${vo.email}"/>
						</div>
						<button type="submit" class="btn btn-primary">수정완료</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>