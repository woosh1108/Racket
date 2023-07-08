package com.multi.racket.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.emailCertified.MailSendService;
import com.multi.racket.manage.ManageRepository;
import com.multi.racket.repository.MemberRepository;
import com.multi.racket.signup.SignUpService;

@Controller
public class SignupController {
	SignUpService service;
	ManageRepository managerepository;
	MailSendService mailservice;
	MemberRepository memberrepository;
	@Autowired
	public SignupController(SignUpService service, ManageRepository managerepository, MailSendService mailservice,
			MemberRepository memberrepository) {
		super();
		this.service = service;
		this.managerepository = managerepository;
		this.mailservice = mailservice;
		this.memberrepository = memberrepository;
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
	public String signup(MemberDTO member,CashDTO cash) {
		service.member_insert(member);
		service.member_signup(member,cash);
		return "thymeleaf/login/login";
	}
	
	// insert - 회원등록하기 뷰
	// 메일 인증이 필요없다.
		@GetMapping("/signup_kakao")
		public String next_kakao(Model model,HttpServletRequest request){
//			List<MemberDTO> list =
//			 String jsonData = request.getParameter("data");
//			 System.out.println(jsonData);
			 
			    return "thymeleaf/signup/signup_kakao";
		}

		// insert - 회원등록하기 api
		@PostMapping("/signup_kakao")
		public String signup_kakao(MemberDTO member,CashDTO cash) {
			service.member_insert(member);
			service.member_signup(member,cash);
			return "thymeleaf/login/login";
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
	@PostMapping("/check-duplicate-Nick")
    public ResponseEntity<MemberDTO> checkDuplicateNick(@RequestParam String memberNick) {
        MemberDTO memberlist = service.findByMemberNick(memberNick);
        System.out.println(memberlist);
        if (memberlist != null) {
            return ResponseEntity.ok(memberlist); // 중복인 경우
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 중복이 아닌 경우
        }
    }
	@PostMapping("/check-duplicate-email")
    public ResponseEntity<MemberDTO> checkDuplicateEmail(@RequestParam String memberEmail) {
        MemberDTO memberlist = service.findByMemberEmail(memberEmail);
        System.out.println(memberlist);
        if (memberlist != null) {
            return ResponseEntity.ok(memberlist); // 중복인 경우
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 중복이 아닌 경우
        }
    }
}
