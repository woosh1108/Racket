package com.multi.racket.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.racket.announcement.AnnouncementDTO;
import com.multi.racket.announcement.AnnouncementService;

@Controller
public class AnnouncementController {
	AnnouncementService announcementService;

	@Autowired
	public AnnouncementController(AnnouncementService announcementService) {
		super();
		this.announcementService = announcementService;
	}

	@GetMapping("/nextPage/announcement")
	public String nextPage(@RequestParam(defaultValue = "0") int currentPage, Model model) {
		int nextPage = currentPage + 1;
		return "redirect:/announcement?pageNo=" + nextPage;
	}

	// 페이징 자동처리
	@GetMapping("/pagingdata/announcement")
	public String announcementPage(Model model) {
		int pageSize = 10; // 페이지 당 공지사항 수 // 추가
		AnnouncementDTO announcelist = (AnnouncementDTO) announcementService.announcementlist(0); // 0추가
		long totalPages = announcementService.getTotalPages(pageSize); //
		model.addAttribute("announcementlist", announcelist);
		model.addAttribute("totalPages", totalPages); //
		return "thymeleaf/announcement/announcement";
	}

	// 공지사항 페이지 - 공지사항 리스트
	// 동적으로 페이지번호 생성함.
	@GetMapping("/announcement")
	public String announcementPage_page(@RequestParam(defaultValue = "0") String pageNo, Model model) {
		int pageNumber = Integer.parseInt(pageNo);
		int pageSize = 10; // 페이지 당 공지사항 수
		if (pageNo == null) {
			pageNo = "0"; // 기본 페이지 번호 설정
			pageNumber = Integer.parseInt(pageNo); // 문자열을 int로 변환
		}

		if (pageNumber > announcementService.getTotalPages(pageSize)) {
			pageNumber = 0; // 페이지 번호가 총 페이지 수를 초과하는 경우 첫 페이지로 설정
		}

		List<AnnouncementDTO> announcelist = announcementService.announcementlist(pageNumber);
		long totalPages = announcementService.getTotalPages(pageSize);
		model.addAttribute("announcementlist", announcelist);
		model.addAttribute("totalPages", totalPages);

		// 현재 페이지가 10을 초과할 경우 ">>" 버튼 추가
		if (pageNumber > 10) {
			model.addAttribute("hasNextPage", true);
		} else {
			model.addAttribute("hasNextPage", false);
		}

		return "thymeleaf/announcement/announcement";
	}

	// 게시글 등록
	@PostMapping("/announcement")
	public String announcementWrite(AnnouncementDTO announcement) {
		// 제목을 입력하지 않으면 자동으로 제목없음으로 들어감.
		String title = announcement.getAnnouncementTitle();

		if (title == null || title.isEmpty()) {
			announcement.setAnnouncementTitle("제목없음");
		}
		
		announcementService.Announcement_insert(announcement);
		return "redirect:/announcement";
	}

	// 게시글 상세보기
	@RequestMapping("/announcement_read")
	public String announcementRead(Integer announcementNo, String state, Model model) {
		AnnouncementDTO announcement = announcementService.read(announcementNo);
		// 조회수 증가
		int views = announcement.getAnnouncementViews();
		announcement.setAnnouncementViews(views + 1);
		announcementService.save(announcement);
		String view = "";
		if (state != null && state.equals("READ")) {
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
		announcementService.delete(announcementNo);
		System.out.println(announcementNo);
		return "redirect:/announcement";
	}

	// 공지사항 수정 페이지로 이동
	@GetMapping("/announcement_update/{announcementNo}")
	public String showUpdateForm(@PathVariable("announcementNo") int announcementNo, Model model) {
		// 공지사항 수정을 위해 해당 공지사항 정보를 가져온다
		AnnouncementDTO updatedata = announcementService.getAnnouncement(announcementNo);
		model.addAttribute("updatedata", updatedata);
		return "thymeleaf/announcement/announcement_update";
	}

	// 공지사항 수정 처리
	@PostMapping("/announcement_update")
	public String updateAnnouncement(@ModelAttribute("updatedata") AnnouncementDTO updatedata) {
		announcementService.update(updatedata);
		return "redirect:/announcement"; // 수정 완료 후 공지사항 목록 페이지로 이동
	}

	// 게시글 검색기능
	@PostMapping("/announcement_search")
	public String search(String data, Model model) {
		List<AnnouncementDTO> searchlist = announcementService.search(data);
		model.addAttribute("searchlist", searchlist);
		return "thymeleaf/announcement/announcement_search";

	}

}
