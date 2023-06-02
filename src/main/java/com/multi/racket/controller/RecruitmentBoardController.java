package com.multi.racket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecruitmentBoardController {
	
	@RequestMapping("/recruitment_write")
	public String recruitmentWrite(Model model) {
		model.addAttribute("recruitmentId", "kim001");
		return "thymeleaf/recruitment_board_write";
	}
	
	@RequestMapping("/recruitment_read")
	public String recruitmentRead(Model model) {
		model.addAttribute("recruitmentId", "kim001");
		model.addAttribute("recruitmentTitle", "축구 같이 하실분??~~");
		model.addAttribute("recruitmentDate", "2023-06-01");
		model.addAttribute("count", 1);
		model.addAttribute("recruitmentContent", "같이하실분은 연락주세여~~~~~");
		return "thymeleaf/recruitment_board_read";
	}
}
