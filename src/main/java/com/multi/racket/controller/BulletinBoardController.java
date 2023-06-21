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
import com.multi.racket.bulletin_board.BulletinBoardDTO;
import com.multi.racket.bulletin_board.BulletinBoardService;

@Controller
public class BulletinBoardController {
	BulletinBoardService bulletinService;

	@Autowired
	public BulletinBoardController(BulletinBoardService bulletinService) {
		super();
		this.bulletinService = bulletinService;
	}

	@GetMapping("/nextPage/bulletin")
	public String nextPage(@RequestParam(defaultValue = "0") int currentPage, Model model) {
		int nextPage = currentPage + 1;
		return "redirect:/bulletin?pageNo=" + nextPage;
	}

	// 페이징 자동처리
	@GetMapping("/pagingdata/bulletin")
	public String bulletinPage(Model model) {
		int pageSize = 10; // 페이지 당 공지사항 수 // 추가
		BulletinBoardDTO bulletinlist = (BulletinBoardDTO) bulletinService.bulletinBoardlist(0);// 0추가
		long totalPages = bulletinService.getTotalPages(pageSize); //
		model.addAttribute("announcementlist", bulletinlist);
		model.addAttribute("totalPages", totalPages); //
		return "thymeleaf/bulletin_board/bulletin_board";
	}

	// 전체게시판 페이지
	@GetMapping("/bulletin")
	public String bulletinPage_page(@RequestParam(defaultValue = "0") String pageNo, Model model) {
		int pageNumber = Integer.parseInt(pageNo);
		int pageSize = 10;
		if (pageNo == null) {
			pageNo = "0";
			pageNumber = Integer.parseInt(pageNo);
		}

		if (pageNumber > bulletinService.getTotalPages(pageSize)) {
			pageNumber = 0;
		}

		List<BulletinBoardDTO> bulletinlist = bulletinService.bulletinBoardlist(pageNumber);
		long totalPages = bulletinService.getTotalPages(pageSize);
		model.addAttribute("bulletinlist", bulletinlist);
		model.addAttribute("totalPages", totalPages);

		// 현재 페이지가 10을 초과할 경우 ">>" 버튼 추가
		if (pageNumber > 10) {
			model.addAttribute("hasNextPage", true);
		} else {
			model.addAttribute("hasNextPage", false);
		}

		return "thymeleaf/bulletin_board/bulletin_board";
	}

	// 게시글 등록
	@PostMapping("/bulletin")
	public String bulletinWrite(BulletinBoardDTO bulletinBoard) {
		// 제목을 입력하지 않으면 자동으로 제목없음으로 들어감.
		String title = bulletinBoard.getBbTitle();

		if (title == null || title.isEmpty()) {
			bulletinBoard.setBbTitle("제목없음");
		}

		bulletinService.Bulletin_insert(bulletinBoard);
		return "redirect:/bulletin";
	}

	// 게시글 상세보기
	@RequestMapping("/bulletin_read")
	public String bulletinRead(Integer bbNo, String state, Model model) {
		BulletinBoardDTO bulletinBoard = bulletinService.read(bbNo);
		// 조회수 증가
		int views = bulletinBoard.getBbViews();
		bulletinBoard.setBbViews(views + 1);
		bulletinService.save(bulletinBoard);
		String view = "";
		if (state != null && state.equals("READ")) {
			view = "thymeleaf/bulletin_board/bulletin_board_read";
		} else {
			view = "";
		}
		model.addAttribute("bulletinBoard", bulletinBoard);
		return view;
	}

	// 전체게시판 글쓰기 페이지
	@RequestMapping("/bulletin_write")
	public String bulletinBoardWritePage(Model model) {
		model.addAttribute("recruitmentId", "kim001");
		return "thymeleaf/bulletin_board/bulletin_board_write";
	}

	// 게시글 삭제
	@PostMapping("/bulletin_delete")
	public String bulletinDelete(@RequestParam("bbNo") int bbNo) {
		// 로그인이 안 되어있으면 로그인페이지로 이동할 수 있도록도 구현해야함.
		bulletinService.delete(bbNo);
		System.out.println(bbNo);
		return "redirect:/bulletin";
	}

	// 전체게시판 수정 페이지로 이동
	@GetMapping("/bulletin_update/{bbNo}")
	public String showUpdateForm(@PathVariable("bbNo") int bbNo, Model model) {
		// 공지사항 수정을 위해 해당 공지사항 정보를 가져온다
		BulletinBoardDTO updatedata = bulletinService.getBulletinBoard(bbNo);
		model.addAttribute("updatedata", updatedata);
		return "thymeleaf/bulletin_board/bulletin_board_update";
	}

	// 전체게시판 수정 처리
	@PostMapping("/bulletin_update")
	public String updateBulletinBoard(@ModelAttribute("updatedata") BulletinBoardDTO updatedata) {
		bulletinService.update(updatedata);
		return "redirect:/bulletin"; // 수정 완료 후 전체게시판 목록 페이지로 이동
	}

	// 게시글 검색기능
	@PostMapping("/bulletin_search")
	public String search(String data, Model model) {
		List<BulletinBoardDTO> searchlist = bulletinService.search(data);
		model.addAttribute("searchlist", searchlist);
		return "thymeleaf/bulletin_board/bulletin_board_search";

	}
}
