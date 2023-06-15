package com.multi.racket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.racket.domain.MemberDTO;
import com.multi.racket.login.MemberService;

@Controller
public class LoginController {
	@Autowired
	MemberService service;

	@GetMapping("/login")
	public String Login() {
		return "thymeleaf/login/login"; // 로그인 페이지
	}

	@PostMapping("/login.do")
	public String login(@RequestParam("memberId") String memberId, @RequestParam("memberPass") String memberPass,
			Model model, HttpServletRequest request) {
		MemberDTO user = service.login(memberId, memberPass);
		if (user != null) {
			// 로그인 성공
			HttpSession session = request.getSession();//세션만들기
			//세션에 데이터 공유하기
			session.setAttribute("user",user);
			return "thymeleaf/main/mainpage"; // 로그인 후 이동할 페이지 URL
		} else {
			// 로그인 실패
			model.addAttribute("msg", "아이디 비밀번호를 확인해주세요.");
			System.out.println("로그인실패");
			return "thymeleaf/login/login"; // 로그인 페이지
		}
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request) {

	    HttpSession session = request.getSession();
	    if (session != null) {
	        session.invalidate();   // 세션 날림
	    }

	    return "redirect:/main";
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
