package com.multi.racket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	@GetMapping("/main")
	public String index() {
		return "thymeleaf/main/mainpage";
	}

	// 관리자페이지 - 블랙리스트

		@GetMapping("/blacklist")
		public String blacklist() {
			return "thymeleaf/manager/blacklist";
		}
		
		// 구장 예약하기
		@GetMapping("/reservation")
		public String reservation() {
			return "thymeleaf/manager/reservation";
		}
		
		// 구장 예약 참여하기
		@GetMapping("/reservation_p")
		public String reservationParticipant() {
			return "thymeleaf/manager/reservation_participant";
		}
		
		// 강습 예약하기
		@GetMapping("/lesson")
		public String lesson() {
			return "thymeleaf/manager/lesson";
		}
		
		// 강습 예약 참여하기
		@GetMapping("/lesson_p")
		public String lessonParticipant() {
			return "thymeleaf/manager/lesson_participant";
		}
		
	@GetMapping("/test1")
	public String test1() {
		return "thymeleaf/include/calendar";
	}
	
}
