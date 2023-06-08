<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  
</head>
<body>
	<div align="center">
		<img alt="" src="/racket/images/racket.png">
	</div>
	<div class="container" style="margin-bottom: 100px" >
		<div class="col-6 mx-auto">
			<form class="form-control" action="login" method="post">
				<label for="member_id" class="form-label">아이디</label> <input
					type="text" name="member_id" id="member_id" class="form-control">
				<label for="member_pass" class="form-label">비밀번호</label> <input
					type="text" name="member_pass" id="member_pass" class="form-control">
				<div class="d-grid">
					<button class="btn btn-primary btn-block my-3">로그인</button>
				</div>
				<div align="center">
					<button onclick="location.href='/rakect/login/findid'" id="btnFindId" style="width: 32%"  type="reset" class="btn btn-outline-dark">아이디찾기</button>
					<button onclick="/rakect/login/findPass" id="btnFindPw" style="width: 32%" type="reset" class="btn btn-outline-dark">비밀번호 찾기</button>
					<!-- 지현님메핑경로넣기 -->
					<button onclick="" id="btnJoin" style="width: 32%" type="reset" class="btn btn-outline-dark">회원가입</button>
				</div>
				<div align="center" style="margin-top: 8px">
					<!-- 지현님메핑경로넣기 -->
					<button id="btnLoginNaver" style="width: 49%" type="reset" class="btn btn-success">네이버로그인</button>
					<!-- 지현님메핑경로넣기 -->
					<button id="btnLoginKakao" style="width: 49%" type="reset" class="btn btn-warning">카카오톡로그인</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>