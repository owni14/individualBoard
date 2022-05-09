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
<script>
$(function() {
	var isPushed = false; // 아이디 중복체크 버튼 눌렀는지
	var checkedId = "";
	$("#frm_join").submit(function() {
		if (!isPushed || checkedId != $("#id").val()) {
			$("#span_message").text("아이디 중복체크를 해주세요.")
						 	  .css("color", "purple");
			$("#img_message").attr("src", "/mypro17/images/angry.png");
			isPushed = false;
			checkedId = "";
			return false; // 폼 전송 안함
		}
	});
	$("#btnCheckDupId").click(function() {
// 		console.log("click");
		var id = $("#id").val();
		console.log(id);
		var url = "/pro17/member/check_dup_id";
		var sendData = {
				"id" : id
		};
		
		$.post(url, sendData, function(receivedData) {
// 			console.log("receivedData: " + receivedData);
			var span_message = $("#span_message");
			var img_message = $("#img_message");
			if (receivedData == "true") {
				span_message.text("이미 사용중인 아이디입니다.")
					        .css("color", "red");
				img_message.attr("src", "/pro17/images/angry.png");
				var isPushed = false;
			} else {
				span_message.text("사용가능한 아이디입니다.")
							.css("color", "blue");
				img_message.attr("src", "/pro17/images/smile.png");
				var isPushed = true;
				checkedId = $("#id").val();
			}
		});
		
		/* 
		var id = $("input[id=id]").val();
		url = "/pro17/member/check_id";
		sendData = {
				"id" : id
		};
		var that = $(this).next();
		$.post(url, sendData, function(receivedData) {
			console.log("receivedData:" + receivedData);
			if (id == "") {
				that.css("color", "red").html("사용할 수 없는 아이디입니다.");
			} else if (receivedData == "true") {
				that.css("color", "red").html("중복된 아이디입니다.");
			} else if (receivedData == "false"){
				that.css("color", "blue").html("사용가능한 아이디입니다.");
			}
		});
	});
	
	$("#register").click(function() {
		console.log("register");
		var id = $("input[id=id]").val();
		var that = $(this).next();
		var span = $("#alert").html();
		console.log("span:" + span);
		if (id == "" || span == "중복된 아이디입니다.") {
			that.css("color", "red").html("아이디 중복을 확인해주세요.");
		}
// 		$(this).attr("type", "submit");
 */
 
	});
});
</script>
</head>
<body>
	<div class="container-fluid">
		
			<div class="col-md-12">
				<div class="jumbotron">
					<h2>회원 가입 양식</h2>
					<p>아래 항목을 빠짐없이 작성해주세요.</p>
					<p>
						<a class="btn btn-primary btn-large" href="/mypro17/mymember/list">회원목록</a>
					</p>

				</div>
				<div class="row">
					<div class="col-md-12">
						<form role="form" action="/mupro17/mymember/join_run" method="post" id="frm_join">
							<div class="form-group">
								<label for="id"> 아이디 </label> 
								<input type="text" class="form-control" id="id" name="id" />
								<button type="button" id="btnCheckDupId">아이디 중복체크</button>
								<span id="span_message"></span>
								<img id="img_message">
<!-- 								<span style="font-weight: bold;" id="alert"></span> -->
							</div>
							<div class="form-group">
								<label for="pwd"> 패스워드 </label> 
								<input type="password" class="form-control" id="pwd" name="pwd" />
							</div>
							<div class="form-group">
								<label for="name"> 이름 </label> 
								<input type="text" class="form-control" id="name" name="name" />
							</div>
							<div class="form-group">
								<label for="email"> 이메일 </label> 
								<input type="email" class="form-control" id="email" name="email" />
							</div>
							<button type="submit" class="btn btn-primary" id="register">가입완료</button>
<!-- 							<span style="font-weight: bold"></span> -->
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>