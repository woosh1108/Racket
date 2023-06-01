<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

 
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <link rel="stylesheet" href="/racket/common/css/main.css" />
<style>
	/* Remove the navbar's default margin-bottom and rounded borders */
	.navbar {
		margin-bottom: 0;
		border-radius: 0;	
	}
	body{
		color: black;
	}
	/*  #toparea{
		padding: 30px;
	}  */
	/* body{
		background-color: #edeef1
	} */
</style>
<title>Insert title here</title>
</head>
<body>
	<div>
		<!-- 이곳에 top화면을 연결하세요" -->
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</div>
	<div>
			<tiles:insertAttribute name="side"></tiles:insertAttribute>
			<div class="row" style="margin-left: auto;margin-right: auto;">
				<div class="col-sm-2"></div>
			 	<div class="col-sm-8" height: 800px;">
					<tiles:insertAttribute name="content"></tiles:insertAttribute>
			 		<!-- 이곳에 content화면을 연결하세요" -->		 	</div>
			 		
			</div>
			 
	</div>
	<div>
		<!-- 이곳에 footer화면을 연결하세요" -->
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>
</body>
</html>




