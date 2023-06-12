package com.multi.racket.manager;

import org.springframework.web.bind.annotation.GetMapping;

public class managerController {
	// 관리자페이지 - 블랙리스트
	@GetMapping("/blacklist")
	public String blacklist() {
		return "thymeleaf/manager/blacklist";
	}
}
