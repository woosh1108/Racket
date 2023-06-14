package com.multi.racket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.racket.domain.MemberDTO;
import com.multi.racket.emailCertified.MailSendService;
import com.multi.racket.signup.SignUpService;

@Controller
public class SignupController {
	SignUpService service;
	MailSendService mailservice;
//	EmailService emailservice;
//	@Autowired
//	public signupController(SignUpService service) {
//		super();
//		this.service = service;
//	}
//	@Autowired
//	public signupController(SignUpService service, EmailService emailservice) {
//		super();
//		this.service = service;
//		this.emailservice = emailservice;
//	}
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

	@GetMapping("/associate")
	public String associate() {
		return "thymeleaf/signup/associate";
	}
//	@GetMapping("login/mailConfirm")
//	public String mailform() {
//		return "thymeleaf/signup/mail";
//	}
	// 나중에 삭제할예정

//	//이메일 인증
//		@GetMapping("/mailCheck")
//		@ResponseBody
//		public void mailCheck(String email) {
//			System.out.println("이메일 인증 요청이 들어옴!");
//			System.out.println("이메일 인증 이메일 : " + email);
//		}
//	@PostMapping("login/mailConfirm")
//	@ResponseBody
//    public String mailConfirm(@RequestBody EmailAuthRequestDto emailDto) throws MessagingException, UnsupportedEncodingException {
//
//        String authCode = emailservice.sendEmail(emailDto.getEmail());
//        return authCode;
//    }
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
}
