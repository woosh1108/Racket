package com.multi.racket.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class loginController {

	// 로그인
	@RequestMapping("/login")
	public String login() {
		return "thymeleaf/login/login";
	}

	// 이메일 인증 -> 찾은 아이디 보여주기
	@RequestMapping("/login/findId2")
	public String findId2() {
		return "thymeleaf/login/login_findId2";
	}

	// 아이디 찾기 이메일인증하기
	@RequestMapping("/login/findId")
	public String findId() {
		return "thymeleaf/login/login_findId";
	}

	// 이메일 인증 -> 비밀번호 새 비밀번호 설정
	@RequestMapping("/login/findPass")
	public String findPass() {
		return "thymeleaf/login/login_findPass";

	}

	// 비밀번호 찾기 이메일인증하기
	@RequestMapping("/login/findPass2")
	public String findPass2() {
		return "thymeleaf/login/login_findPass2";
	}
}
