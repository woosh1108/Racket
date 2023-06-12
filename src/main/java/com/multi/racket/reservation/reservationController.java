package com.multi.racket.reservation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class reservationController {
	// 구장 예약하기
	@GetMapping("/reservation")
	public String reservation() {
		return "thymeleaf/reservation/reservation";
	}

	// 구장 예약 참여하기
	@GetMapping("/reservation_p")
	public String reservationParticipant() {
		return "thymeleaf/reservation/reservation_participant";
	}
}
