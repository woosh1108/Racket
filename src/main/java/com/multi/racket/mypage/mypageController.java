package com.multi.racket.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mypageController {

	// 내정보보기 - 수정x
	@RequestMapping("/mypage/info")
	public String myInfo() {
		return "thymeleaf/mypage/myInfo";
	}

	// 내정보 수정하기
	@RequestMapping("/mypage/change")
	public String myInfoChange() {
		return "thymeleaf/mypage/myInfo_change";
	}

	// 내 캐쉬내역보기 - 충전가능
	@RequestMapping("/mypage/cash")
	public String myCash() {
		return "thymeleaf/mypage/myCash";
	}

	// 내 매치보기 - 신고가능
	@RequestMapping("/mypage/match")
	public String myMatch() {
		return "thymeleaf/mypage/myMatch";
	}
}
