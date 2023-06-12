package com.multi.racket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.racket.associate.AssociateService;
import com.multi.racket.domain.stadiumDTO;

@Controller
public class TestController {
	AssociateService assoservice;
	@Autowired
	public TestController(AssociateService assoservice) {
		super();
		this.assoservice = assoservice;
	}


	@GetMapping("/main")
	public String index_test(Model model) {
		List<stadiumDTO> list = assoservice.findAll();
		model.addAttribute("stadiumlist",list);
		return "thymeleaf/main/mainpage";
	}
	
	
	@GetMapping("/main_intro")
	public String main_intro() {
		return "thymeleaf/main/main_intro";
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
	
}
