<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>홈페이지 JSP</title>
    <!-- DatePicker CSS -->
    <link href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css" rel="stylesheet">
    <!-- DatePicker JS -->
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
    <!-- jQuery UI CSS -->
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<!-- jQuery and jQuery UI JS -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
	<link href="/racket/common/css/carousel.css" rel="stylesheet">
	<link href="/racket/common/css/mainpage.css" rel="stylesheet">
</head>
<body>
    <!-- Carousel / 슬라이드바 -->
	<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
		  <div class="carousel-indicators">
		    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
		    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
		    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
		  </div>
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img src="/racket/images/event1.jpg" class="d-block w-100" alt="Image">
		    </div>
		    <div class="carousel-item">
		      <img src="/racket/images/event2.jpg" class="d-block w-100" alt="Image">
		    </div>
		    <div class="carousel-item">
		      <img src="/racket/images/event3.jpg" class="d-block w-100" alt="Image">
		    </div>
		  </div>
		  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="visually-hidden">Previous</span>
		  </button>
		  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="visually-hidden">Next</span>
		  </button>
	</div>
    
   <!-- 검색바 -->
    <div class="container mt-5">
        <form class="d-flex">
            <input class="form-control me-2" type="search" placeholder="구장을 검색하세요" aria-label="검색">
            <button class="btn btn-outline-success" type="submit" id="search">검색</button>
        </form>
    </div>
    
    
	<!-- <div class="container mt-5">
    <p>날짜를 선택해주세요:</p>
    <input type="text" class="form-control" id="datePickerImg">
    <script>
        $("#datePickerImg").datepicker({
            showOn: "button",
            buttonImage: "calendar.png",
            buttonImageOnly: true,
            buttonText: "날짜 선택"
        });
    </script>
	</div>
	
    달력
    <div class="container mt-5">
        <p>날짜를 선택해주세요:</p>
        <input type="text" class="form-control" id="datePicker">
        <script>
            flatpickr('#datePicker', {});
        </script>
    </div>
	
    <script type="text/javascript" src="../test/date.js"></script> !-->
</body>
</html>