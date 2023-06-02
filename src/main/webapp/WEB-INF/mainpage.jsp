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
    
    <!-- 날짜 -->
    <!-- <div class="container mt-5">
    	<table class="calendar" border="1">
    		<tr>
    			<td>06.02</td>
    			<td>06.03</td>
    			<td>06.04</td>
    			<td>06.05</td>
    			<td>06.06</td>
    			<td>06.07</td>
    			<td>06.08</td>
    			<td>06.09</td>
    			<td>06.10</td>
    			<td>06.11</td>
    		</tr>
    	</table>
    </div> -->
    
    <div class="calendar">
			<!-- 1월 -->
			<div class="calendar__month">
				<div class="calendar__day">월</div>
				<div class="calendar__date">1</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">화</div>
				<div class="calendar__date">2</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">수</div>
				<div class="calendar__date">3</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">목</div>
				<div class="calendar__date">4</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">금</div>
				<div class="calendar__date">5</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">토</div>
				<div class="calendar__date">6</div>
			</div>
			<div class="calendar__month today">
				<div class="calendar__day">일</div>
				<div class="calendar__date">7</div>
			</div>
			<!-- 2월 -->
			<div class="calendar__month">
				<div class="calendar__day">월</div>
				<div class="calendar__date">8</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">화</div>
				<div class="calendar__date">9</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">수</div>
				<div class="calendar__date">10</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">목</div>
				<div class="calendar__date">11</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">금</div>
				<div class="calendar__date">12</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">토</div>
				<div class="calendar__date">13</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">일</div>
				<div class="calendar__date">14</div>
			</div>
			<!-- 3월 -->
			<div class="calendar__month">
				<div class="calendar__day">월</div>
				<div class="calendar__date">15</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">화</div>
				<div class="calendar__date">16</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">수</div>
				<div class="calendar__date">17</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">목</div>
				<div class="calendar__date">18</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">금</div>
				<div class="calendar__date">19</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">토</div>
				<div class="calendar__date">20</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">일</div>
				<div class="calendar__date">21</div>
			</div>
			<!-- 4월 -->
			<div class="calendar__month">
				<div class="calendar__day">월</div>
				<div class="calendar__date">22</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">화</div>
				<div class="calendar__date">23</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">수</div>
				<div class="calendar__date">24</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">목</div>
				<div class="calendar__date">25</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">금</div>
				<div class="calendar__date">26</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">토</div>
				<div class="calendar__date">27</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">일</div>
				<div class="calendar__date">28</div>
			</div>
			<!-- 5월 -->
			<div class="calendar__month">
				<div class="calendar__day">월</div>
				<div class="calendar__date">29</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">화</div>
				<div class="calendar__date">30</div>
			</div>
			<div class="calendar__month">
				<div class="calendar__day">수</div>
				<div class="calendar__date">31</div>
			</div>
		</div>
    	
    
   <!-- 검색바 -->
    <div class="container mt-5">
        <form class="d-flex">
            <input class="form-control me-2" type="search" placeholder="구장을 검색하세요" aria-label="검색">
            <button class="btn btn-outline-success" type="submit" id="search">검색</button>
        </form>
   </div>
   
   <!-- 구장 소개 -->
   <h3 class="stadium-list mt-5 mb-5">구장 목록</h3>
   <div class="stadium">
   		<div class="stadium-pic"><img src="/racket/images/stadium1.jpg" class="stadium-picture"/></div>
   		<div class="stadium-info">
   			<h3>별빛바람구장123</h3>
   			<h4>장소 : XX번지</h4>
   			<h4>평점 : 5점</h4>
   			<h2>13000 원</h2>
   		</div>
   </div>
   <div class="stadium">
   		<div class="stadium-pic"><img src="/racket/images/stadium1.jpg" class="stadium-picture"/></div>
   		<div class="stadium-info">
   			<h3>별빛바람구장123</h3>
   			<h4>장소 : XX번지</h4>
   			<h4>평점 : 5점</h4>
   			<h2>13000 원</h2>
   		</div>
   </div>
   <div class="stadium">
   		<div class="stadium-pic"><img src="/racket/images/stadium1.jpg" class="stadium-picture"/></div>
   		<div class="stadium-info">
   			<h3>별빛바람구장123</h3>
   			<h4>장소 : XX번지</h4>
   			<h4>평점 : 5점</h4>
   			<h2>13000 원</h2>
   		</div>
   </div>
   <div class="stadium">
   		<div class="stadium-pic"><img src="/racket/images/stadium1.jpg" class="stadium-picture"/></div>
   		<div class="stadium-info">
   			<h3>별빛바람구장123</h3>
   			<h4>장소 : XX번지</h4>
   			<h4>평점 : 5점</h4>
   			<h2>13000 원</h2>
   		</div>
   </div>
   <div class="stadium">
   		<div class="stadium-pic"><img src="/racket/images/stadium1.jpg" class="stadium-picture"/></div>
   		<div class="stadium-info">
   			<h3>별빛바람구장123</h3>
   			<h4>장소 : XX번지</h4>
   			<h4>평점 : 5점</h4>
   			<h2>13000 원</h2>
   		</div>
   </div>
   <div class="stadium">
   		<div class="stadium-pic"><img src="/racket/images/stadium1.jpg" class="stadium-picture"/></div>
   		<div class="stadium-info">
   			<h3>별빛바람구장123</h3>
   			<h4>장소 : XX번지</h4>
   			<h4>평점 : 5점</h4>
   			<h2>13000 원</h2>
   		</div>
   </div>
   <div class="stadium">
   		<div class="stadium-pic"><img src="/racket/images/stadium1.jpg" class="stadium-picture"/></div>
   		<div class="stadium-info">
   			<h3>별빛바람구장123</h3>
   			<h4>장소 : XX번지</h4>
   			<h4>평점 : 5점</h4>
   			<h2>13000 원</h2>
   		</div>
   </div>
   <div class="stadium">
   		<div class="stadium-pic"><img src="/racket/images/stadium1.jpg" class="stadium-picture"/></div>
   		<div class="stadium-info">
   			<h3>별빛바람구장123</h3>
   			<h4>장소 : XX번지</h4>
   			<h4>평점 : 5점</h4>
   			<h2>13000 원</h2>
   		</div>
   </div>
   <div class="stadium">
   		<div class="stadium-pic"><img src="/racket/images/stadium1.jpg" class="stadium-picture"/></div>
   		<div class="stadium-info">
   			<h3>별빛바람구장123</h3>
   			<h4>장소 : XX번지</h4>
   			<h4>평점 : 5점</h4>
   			<h2>13000 원</h2>
   		</div>
   </div>
   <div class="stadium">
   		<div class="stadium-pic"><img src="/racket/images/stadium1.jpg" class="stadium-picture"/></div>
   		<div class="stadium-info">
   			<h3>별빛바람구장123</h3>
   			<h4>장소 : XX번지</h4>
   			<h4>평점 : 5점</h4>
   			<h2>13000 원</h2>
   		</div>
   </div>
   <div class="stadium">
   		<div class="stadium-pic"><img src="/racket/images/stadium1.jpg" class="stadium-picture"/></div>
   		<div class="stadium-info">
   			<h3>별빛바람구장123</h3>
   			<h4>장소 : XX번지</h4>
   			<h4>평점 : 5점</h4>
   			<h2>13000 원</h2>
   		</div>
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