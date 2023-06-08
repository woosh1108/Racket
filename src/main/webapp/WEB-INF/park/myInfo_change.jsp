<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/racket/common/assets/css/main.css" />
<noscript>
	<link rel="stylesheet" href="/racket/common/assets/css/noscript.css" />
</noscript>
</head>
<body class="is-preload">
	<div id="wrapper">
		<div class="col-lg-9">
			<div class="col-6 mx-auto">
				<section>
					<div class="content">
						<ul class="actions">
							<li><a href="#" class="button">내정보</a></li>
							<li><a href="#" class="button">정보수정</a></li>
							<li><a href="#" class="button">충전</a></li>
							<li><a href="#" class="button">매칭</a></li>
						</ul>
					</div>
				</section>
			</div>
			<div class="container">
				<div class="box">
					<div class="col-6 mx-auto">
						<form class="form-control" action="myInfo" method="post">
							<label for="member_nick" class="form-label">아이디</label>
							<div class="box"></div>
							<hr />
							<label for="member_nick" class="form-label">이름</label>
							<div class="box"></div>
							<hr />
							<label for="member_nick" class="form-label">닉네임</label> <input
								type="text" name="member_nick" id="member_nick"
								class="form-control" placeholder="">
							<hr />
							<label for="member_age" class="form-label">나이</label> <input
								type="text" name="member_age" id="member_age"
								class="form-control" placeholder="">
							<hr />
							<label for="member_phone" class="form-label">전화번호</label> <input
								type="text" name="member_phone" id="member_phone"
								class="form-control" placeholder="">
							<hr />
							<label for="member_addr" class="form-label">주소</label> <input
								type="text" name="member_addr" id="member_addr"
								class="form-control" placeholder="">
							<hr />
							<label for="member_addr" class="form-label">이메일</label>
							<div class="box"></div>
							<hr />
							<label for="member_addr" class="form-label">등급</label>
							<div class="box"></div>
							<hr />
							<div class="field" style="text-align: center;">
								<a href="#"> <input type="button" name="submit" id="submit"
									value="정보수정" />
								</a>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Scripts -->
	<script src="/racket/common/assets/js/jquery.min.js"></script>
	<script src="/racket/common/assets/js/jquery.scrollex.min.js"></script>
	<script src="/racket/common/assets/js/jquery.scrolly.min.js"></script>
	<script src="/racket/common/assets/js/browser.min.js"></script>
	<script src="/racket/common/assets/js/breakpoints.min.js"></script>
	<script src="/racket/common/assets/js/util.js"></script>
	<script src="/racket/common/assets/js/main.js"></script>

	<!-- Note: Only needed for demo purposes. Delete for production sites. -->
	<script src="/racket/common/assets/js/demo.js"></script>
</body>
</html>