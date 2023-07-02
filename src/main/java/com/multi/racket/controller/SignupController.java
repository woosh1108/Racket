package com.multi.racket.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.racket.domain.MemberDTO;
import com.multi.racket.emailCertified.MailSendService;
import com.multi.racket.signup.SignUpService;

@Controller
public class SignupController {
	SignUpService service;
	MailSendService mailservice;
	@Autowired
	public SignupController(SignUpService service, MailSendService mailservice) {
		super();
		this.service = service;
		this.mailservice = mailservice;
	}

	// 회원가입 인증
	@RequestMapping("/signauth")
	public String signaauth() {
		return "thymeleaf/signup/sign-auth";
	}

	@GetMapping("/agreepopup")
	public String agreepopup() {
		return "thymeleaf/signup/sign-agreement";
	}

	// 회원가입 폼

	// insert - 회원등록하기 뷰
	@GetMapping("/signup")
	public String next() {
		return "thymeleaf/signup/signup";
	}

	// insert - 회원등록하기 api
	@PostMapping("/signup")
	public String signup(MemberDTO member) {
		service.member_insert(member);
		return "thymeleaf/main/mainpage";
	}
	
	// insert - 회원등록하기 뷰
		@GetMapping("/signup_kakao")
		public String next_kakao(Model model){
			    return "thymeleaf/signup/signup_kakao";
		}

		// insert - 회원등록하기 api
		@PostMapping("/signup_kakao")
		public String signup_kakao(MemberDTO member) {
			service.member_insert(member);
			return "thymeleaf/main/mainpage";
		}
	
	// 회원가입 페이지 이동
	@GetMapping("/userJoin")
	public String userJoin() {
		return "thymeleaf/signup/sign-auth";
	}

	// 이메일 인증
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheck(String email) {
		System.out.println("이메일 인증 요청이 들어옴!");
		System.out.println("이메일 인증 이메일 : " + email);
		return mailservice.joinEmail(email);

	}
	
	@PostMapping("/check-duplicate-id")
    public ResponseEntity<MemberDTO> checkDuplicateId(@RequestParam String memberId) {
        MemberDTO memberlist = service.findMemberByMemberId(memberId);
        System.out.println(memberlist);
        if (memberlist != null) {
            return ResponseEntity.ok(memberlist); // 중복인 경우
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 중복이 아닌 경우
        }
    }
}
