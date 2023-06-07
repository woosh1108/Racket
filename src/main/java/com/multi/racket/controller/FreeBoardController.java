package com.multi.racket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FreeBoardController {
	
	//나중에 ModelAndView로 받아와서 공유해서 보여줘야함	
	@RequestMapping("/free") //jsp
	public String showFreeBoard(Model model) {
		model.addAttribute("freeboardlist", "공유된 데이터 불러오기");
		return "thymeleaf/free_board"; //tiles;
	}
	
	@RequestMapping("/recruitment")
	public String toRecruitment(Model model) {
		model.addAttribute("recruitmentboardlist", "공유된 데이터 가져오기");
		return "thymeleaf/recruitment_board";
	}
	//나중에 자유게시판이랑 운동모집게시판 컨트롤러랑 html또 따로 만들어야함
	// 자유게시판 -> 운동모집게시판 / 운동모집게시판 -> 자유게시판 이동하는것도 위에 지우고 따로 다시 만들어야함.
	@RequestMapping("/free_write")
	public String freeBoardWrite(Model model) {
		model.addAttribute("freeId", "park001"); //나중엔 DB에서 가져와야함
		return "thymeleaf/free_board_write";
	}
	
	@RequestMapping("/free_read")
	public String freeBoardRead(Model model) {
		model.addAttribute("freeReadNo", "1");
		model.addAttribute("freeReadId", "park001");
		model.addAttribute("freeReadDate", "2023.05.31");
		return "thymeleaf/free_board_read";
	}
	
	//여기다가 리스트로 보드 데이터 뿌려줘야함
	
	
	
	//dto에 있는 필드를 불러와
	@PostMapping("/free_read")
	public String postWrite() {
		
		return "redirect:/racket/free_read?bno="; // + read.getBno();
	}
	
	
}	
