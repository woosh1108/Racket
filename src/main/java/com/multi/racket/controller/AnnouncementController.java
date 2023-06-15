package com.multi.racket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	// 공지사항 페이지 - 공지시항 리스트
	@GetMapping("/announcement")
	public String announcementPage(Model model) {
		List<AnnouncementDTO> announcelist = AnnouncementService.announcementlist();
		model.addAttribute("announcementlist", announcelist);
		return "thymeleaf/announcement/announcement";
	}

	// 게시글 등록
	@PostMapping("/announcement")
	public String announcementWrite(AnnouncementDTO announcement) {
		AnnouncementService.Announcement_insert(announcement);
		return "redirect:/announcement";
	}

	// 게시글 상세보기
	@RequestMapping("/announcement_read")
	public String announcementRead(int announcementNo, String state, Model model) {
		AnnouncementDTO announcement = AnnouncementService.read(announcementNo);
		// 조회수 증가
		int views = announcement.getAnnouncementViews();
		announcement.setAnnouncementViews(views + 1);
		AnnouncementService.save(announcement);
		String view = "";
		if (state.equals("READ")) {
			view = "thymeleaf/announcement/announcement_read";
		} else {
			view = "";
		}
		model.addAttribute("announcement", announcement);
		return view;
	}

	// 게시글 등록 페이지
	@GetMapping("/announcement_write")
	public String announcementWrite(Model model) {
		model.addAttribute("freeId", "park001"); // 나중엔 DB에서 가져와야함
		return "thymeleaf/announcement/announcement_write";
	}

	// 게시글 삭제
	@PostMapping("/announcement_delete")
	public String announcementDelete(@RequestParam("announcementNo") int announcementNo) {
		// 로그인이 안 되어있으면 로그인페이지로 이동할 수 있도록도 구현해야함.
		AnnouncementService.delete(announcementNo);
		System.out.println(announcementNo);
		return "redirect:/announcement";
	}
	
//	 // 게시글 수정 페이지
//    @GetMapping("/announcement_update")
//    public String announcementUpdateForm(@RequestParam("announcementNo") int announcementNo, Model model) {
//        AnnouncementDTO updatedata = AnnouncementService.getAnnouncement(announcementNo);
//        model.addAttribute("updatedata", updatedata);
//        return "thymeleaf/announcement/announcement_update";
//    }
//
//    // 게시글 수정
//    @PostMapping("/announcement_update")
//    public String announcementUpdate(@ModelAttribute("announcementDTO") AnnouncementDTO announcementDTO, int announcementNo) {
//        AnnouncementService.update(announcementNo, announcementDTO);
//        return "redirect:/announcement/read?announcementNo=" + announcementDTO.getAnnouncementNo();
//    }
//	

}
