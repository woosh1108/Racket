package com.multi.racket.manager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public class managerController {
	// 관리자페이지 - 블랙리스트
	@RequestMapping("/blacklist")
	public String blacklist() {
		return "thymeleaf/manager/blacklist";
	}
}
