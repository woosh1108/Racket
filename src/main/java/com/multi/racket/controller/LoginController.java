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

	@RequestMapping("/login")
	public String Login() {
		return "thymeleaf/login/login"; // 로그인 페이지
	}

	@PostMapping("/login.do")
	public String login(String memberId, String memberPass, Model model, HttpSession session) {
		MemberDTO user = service.login(memberId, memberPass);
		if (user != null) {
			// 로그인 성공
			// 세션에 데이터 공유하기
			session.setAttribute("user", user);
			return "thymeleaf/main/mainpage"; // 로그인 후 이동할 페이지 URL
		} else {
			// 로그인 실패
			model.addAttribute("msg", "아이디 비밀번호를 확인해주세요.");
			return "thymeleaf/login/login"; // 로그인 페이지
		}
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		if (session != null) {
			session.invalidate(); // 세션 날림
		}

		return "redirect:/main";
	}

	// 이메일 인증 -> 찾은 아이디 보여주기
	@RequestMapping("/login/findId2")
	public String findId2() {
		return "thymeleaf/login/login_findId2";
	}

	// 아이디 찾기 처음 클릭시 보이는 페이지
	@RequestMapping("/login/findId")
	public String findId() {
		return "thymeleaf/login/login_findId";
	}

	// 아이디 찾기 클릭 후 이름 확인하기
	@RequestMapping("/login/findId_nameCheck")
	public String findId_findName(String memberName, String memberEmail1, String memberEmail2
			, String memberEmail, Model model) {
		memberEmail = memberEmail1.concat(memberEmail2); 
		MemberDTO user = service.findId(memberName, memberEmail);
		if (user != null) {
			model.addAttribute("msg", "확인되었습니다.");
		} else {
			model.addAttribute("msg1", "이메일 또는 이름을 다시한번 확인해주세요.");
		}
		
		return "thymeleaf/login/login_findId";
	}

	// 비밀번호 찾기 처음 클릭시 보이는 페이지
	@RequestMapping("/login/findPass")
	public String findPass() {
		return "thymeleaf/login/login_findPass";
	}

	// 비밀번호 찾기 클릭 후 아이디 확인하기
	@RequestMapping("/login/findPass_idCheck")
	public String findPass_findId(String memberId, Model model) {
		System.out.println(memberId);
		boolean check = service.idCheck(memberId);
		if (check) {
			model.addAttribute("msg", "아이디를 확인했습니다.");
		} else {
			model.addAttribute("msg", "존재하지 않는 아이디입니다.");
		}
		System.out.println(check);
		return "thymeleaf/login/login_findPass";
	}

	// 비밀번호 찾기 인증 완료 후 비밀번호 변경하기
	@RequestMapping("/login/findPass2")
	public String findPass2() {
		return "thymeleaf/login/login_findPass2";
	}
}
