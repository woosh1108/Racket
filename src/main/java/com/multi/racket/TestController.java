package com.multi.racket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	@GetMapping("/main")
	public String index() {
		return "main";
	}

	// 구장 예약하기
	@GetMapping("/reservation")
	public String reservation() {
		return "reservation";
	}
	
	// 관리자페이지 - 블랙리스트
	@GetMapping("/blacklist")
	public String blacklist() {
		return "blacklist";
	}
	
	
}
