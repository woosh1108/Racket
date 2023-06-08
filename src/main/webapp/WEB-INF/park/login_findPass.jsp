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
<style type="text/css">
div {
	margin-top: 20px;
}
</style>
</head>
<body>
	<section>
		<header>
			<h3>비밀번호 찾기</h3>
		</header>
		<div class="content">
			<div class="box">

				<div class="field">
					<label for="name">아이디</label> <input type="text" name="name"
						id="name" value="" />
				</div>
				
				<div class="field third">
					<label for="name">이메일</label> <input type="text" name="name"
						id="name" value="" />
				</div>
				<div class="field third">
					<input type="button" name="submit" id="submit"
						value="인증번호발송" />
				</div>
				
				<div class="field third">
					<label for="name">인증번호</label> <input type="text" name="name"
						id="name" value="" />
				</div>
				<div class="field third">
					<input type="button" name="submit" id="submit"
						value="인증번호 확인" />
				</div>
				
				<br /> <br />
				<div class="field" style="text-align: center;">
					<a href="#"> <input type="button" name="submit" id="submit"
						value="비밀번호찾기" />
					</a>
				</div>
			</div>


		</div>
	</section>
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