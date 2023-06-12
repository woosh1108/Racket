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
	@GetMapping("/main_intro")
	public String main_intro() {
		return "thymeleaf/main/main_intro";
	}
	
}
