package com.multi.racket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class signupController {
	// 회원가입 폼
			@GetMapping("/signup")
			public String next() {
				return "thymeleaf/signup/signup";
			}
		
			// 회원가입 동의
			@GetMapping("/signagree")
			public String signagree() {
				return "/sign-agreement";
			}
			// 회원가입 인증
			@RequestMapping("/signauth")
			public String signaauth() {
				return "thymeleaf/signup/sign-auth";
			}
			@GetMapping("/agreepopup")
			public String test() {
				return "thymeleaf/signup/sign-agreement";
			}
}
