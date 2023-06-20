package com.multi.racket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.multi.racket.domain.MemberDTO;
import com.multi.racket.login.MemberService;

@Controller
@RequestMapping("/mypage") // 공유메핑명
@SessionAttributes("user") // 데이터공유명
public class mypageController {
	@Autowired
	MemberService service;

	@Autowired
	public mypageController(MemberService service) {
		super();
		this.service = service;
	}

	// 내정보보기페이지 - 수정x
	@RequestMapping("/info")
	public String myInfo() {
		return "thymeleaf/mypage/myInfo";
	}

	// 내정보 수정페이지
	@RequestMapping("/change")
	public String myInfoChange() {
		return "thymeleaf/mypage/myInfo_change";
	}

	// 내정보 수정하기
	@PostMapping("/change.do")
	public String infoChange(MemberDTO updateInfo, Model model) {
		if(updateInfo!=null) {
			model.addAttribute("msg", "정보변경이 완료되었습니다.");
		}
		service.update(updateInfo);
		return "thymeleaf/main/mainpage";
	}

	// 내 캐쉬내역보기 - 충전가능
	@RequestMapping("/cash")
	public String myCash() {
		return "thymeleaf/mypage/myCash";
	}

	// 내 매치보기 - 신고가능
	@RequestMapping("/match")
	public String myMatch() {
		return "thymeleaf/mypage/myMatch";
	}
}
