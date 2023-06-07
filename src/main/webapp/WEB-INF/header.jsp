<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="/racket/common/css/header.css">
<script>
	function openAgreementPopup() {
		window.open("/racket/signagree", "", "width=600,height=500");
		return false; // 이벤트를 취소하여 더이상 진행되지 않도록 합니다.
	}
</script>
</head>
<body>
	<nav class="navbar fixed-top">
		<div class="nav_logo">
			<a href="/racket/main"><img src="/racket/images/logo1.png"></a>
		</div>
		<div class="nav_main">
			<a href="/racket/main"><img src="/racket/images/racket.png"></a>
		</div>
		<!-- <ul class="nav_item">
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
        </ul> -->
		<div id='group1' class='group1'>
			<a href="#" class="header-link">
				<div id="rectangle1" class="rectangle1">
					<h1>로그인</h1>
				</div>
			</a>
			<div id='login' class='login'></div>
		</div>
		<div id='group2' class='group2'>
			<a href="/signagree" onclick="return openAgreementPopup();"
				class="header-link">
				<div id='rectangle2' class='rectangle2'>
					<h1>회원가입</h1>
				</div>
			</a>
			<div id='sign-in' class='sign-in'></div>
		</div>

		<!--  <ul class="nav_links">
            <li><a href="#">
            <img src="/racket/images/sign-in.png" style="width:50px" style="height:50px"></a></li>
        </ul> -->


	</nav>
</body>
</html>