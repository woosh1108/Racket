<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
	integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="/racket/common/css/header.css" />
<style>
/* Remove the navbar's default margin-bottom and rounded borders */
/* .navbar {
	margin-bottom: 0;
	border-radius: 0;
} */
	#sign{
		height:50px;
		width:50px;	
		margin-right: 6em;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<a class="navbar-brand" href="#"><img src="/racket/images/racket.png"></a>
	<nav class="navbar navbar-expand-sm bg-info navbar-dark">
		<div class="container-fluid">
			<!-- <a class="navbar-brand" href="#"><img src="/racket/images/racket.png" id="logo"></a> -->
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-end"
				id="collapsibleNavbar">
				<ul class="navbar-nav">
					<!-- <li class="nav-item"><a class="nav-link" href="#">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="#">About</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Resiter</a> -->
					</li>
					<li class="nav-item"><a class="nav-link" href="#"><img src="/racket/images/game.png" id="sign"></a></li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>