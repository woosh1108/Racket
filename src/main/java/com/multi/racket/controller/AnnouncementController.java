package com.multi.racket.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.racket.announcement.AnnouncementDTO;
import com.multi.racket.announcement.AnnouncementService;

@Controller
public class AnnouncementController {
	AnnouncementService AnnouncementService;

	@Autowired
	public AnnouncementController(AnnouncementService announcementService) {
		super();
		AnnouncementService = announcementService;
	}
	
	//공지사항 페이지 - 공지시항 리스트
	@RequestMapping("/announcement")
	public String announcementPage(Model model) {
		List<AnnouncementDTO> announcelist = AnnouncementService.announcementlist();
		model.addAttribute("announcementlist", announcelist);
		return "thymeleaf/announcement";
	}

	// 게시글 등록
	@PostMapping("/board/announcement_write")
	public String AnnouncementWrite(AnnouncementDTO announcement) {
		AnnouncementService.Announcement_insert(announcement);
		return "thymeleaf/announcement";
	}

	// 나중에 자유게시판이랑 운동모집게시판 컨트롤러랑 html또 따로 만들어야함
	// 자유게시판 -> 운동모집게시판 / 운동모집게시판 -> 자유게시판 이동하는것도 위에 지우고 따로 다시 만들어야함.
	// 게시글 등록 뷰
	@GetMapping("/announcement_write")
	public String announcementWrite(Model model) {
		model.addAttribute("freeId", "park001"); // 나중엔 DB에서 가져와야함
		return "thymeleaf/announcement_write";
	}

	@RequestMapping("/bulletin")
	public String toBulletin(Model model) {
		model.addAttribute("recruitmentboardlist", "공유된 데이터 가져오기");
		return "thymeleaf/bulletin_board";
	}

	@RequestMapping("/announcement_read")
	public String announcementRead(Model model) {
		model.addAttribute("freeReadNo", "1");
		model.addAttribute("freeReadId", "park001");
		model.addAttribute("freeReadDate", "2023.05.31");
		return "thymeleaf/announcement_read";
	}

	// 여기다가 리스트로 보드 데이터 뿌려줘야함

//	//dto에 있는 필드를 불러와
//	@PostMapping("/free_read")
//	public String postWrite() {
//		
//		return "redirect:/racket/free_read?bno="; // + read.getBno();
//	}

//	@PostMapping("/announcement.insert")
//	public String announceWrite(AnnounceDTO announce) {
//		System.out.println(announce.getFb_title());
//		return "";
//	}

}
