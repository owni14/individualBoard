<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write_from.jsp</title>
<%@ include file="/WEB-INF/views/include/bootstrap_cdn.jsp" %>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="jumbotron card card-block">
					<h2>글쓰기</h2>
					<div>아래 항목을 빠짐 없이 작성해주세요.</div>
					<p>
						<a class="btn btn-primary btn-large" href="/mypro17/myboard/list">글목록</a>
					</p>
				</div>
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<form role="form" action="/mypro17/myboard/write_run" method="post">
							<div class="form-group">
								<label for="b_title"> 글제목 </label> <input
									type="text" class="form-control" name="b_title" />
							</div>
							<div class="form-group">
								<label for="b_content"> 글내용</label> <input
									type="text" class="form-control" name="b_content" />
							</div>
							<div class="form-group">
								<label for="exampleInputFile"> 첨부파일 </label> <input
									type="file" class="form-control-file" id="exampleInputFile" />
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