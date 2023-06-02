package com.multi.racket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	@GetMapping("/main")
	public String index() {
		return "main";
	}
	@GetMapping("/test")
	public String test() {
		return "/sign-agreement";
	}
	@GetMapping("/next")
	public String next() {
		return "register";
	}
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
}
