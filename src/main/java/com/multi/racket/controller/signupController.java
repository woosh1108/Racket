package com.multi.racket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class signupController {
	// 회원가입 폼
			@GetMapping("/next")
			public String next() {
				return "register";
			}
		
			// 회원가입 동의
			@GetMapping("/signagree")
			public String signagree() {
				return "/sign-agreement";
			}
			// 회원가입 인증
			@RequestMapping("/signauth")
			public String signaauth() {
				return "signAuth";
			}
			@GetMapping("/test")
			public String test() {
				return "thymeleaf/signup/sign-agreement";
			}
}
