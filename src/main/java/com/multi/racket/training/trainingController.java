package com.multi.racket.training;

import org.springframework.web.bind.annotation.GetMapping;

public class trainingController {
	// 강습 예약하기
	@GetMapping("/training")
	public String lesson() {
		return "thymeleaf/reservation/training";
	}

	// 강습 예약 참여하기
	@GetMapping("/training")
	public String lessonParticipant() {
		return "thymeleaf/reservation/training_participant";
	}
}
