package com.multi.racket.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BulletinBoardController {
	
	//전체게시판 페이지
	@RequestMapping("/bulletin")
	public String bulletinPage(Model model) {
		model.addAttribute("recruitmentboardlist", "공유된 데이터 가져오기");
		return "thymeleaf/bulletin_board/bulletin_board";
	}
	
	//전체게시판 글쓰기 페이지
	@RequestMapping("/bulletin_write")
	public String recruitmentWritePage(Model model) {
		model.addAttribute("recruitmentId", "kim001");
		return "thymeleaf/bulletin_board/bulletin_board_write";
	}
	
	//전체게시판 글상세보기 페이지
	@RequestMapping("/bulletin_read")
	public String recruitmentReadPage(Model model) {
		model.addAttribute("recruitmentId", "kim001");
		model.addAttribute("recruitmentTitle", "축구 같이 하실분??~~");
		model.addAttribute("recruitmentDate", "2023-06-01");
		model.addAttribute("count", 1);
		model.addAttribute("recruitmentContent", "같이하실분은 연락주세여~~~~~");
		return "thymeleaf/bulletin_board/bulletin_board_read";
	}
	
}
