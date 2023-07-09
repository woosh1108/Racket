package com.multi.racket.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.racket.domain.InquiryDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.inquiry.InquiryPageService;
import com.multi.racket.inquiry.PageDTO;

@Controller
public class InquiryPageController {
	InquiryPageService service;

	@Autowired
	public InquiryPageController(InquiryPageService service) {
		super();
		this.service = service;
	}

	@GetMapping("/inquiryboard")
	public String inqboard(Model model, String pageNo, HttpSession session) {
		PageDTO page = service.findAll(Integer.parseInt(pageNo));
		List<InquiryDTO> list = page.getList();
		int totalPageNumber = page.getTotalPageNumber();
		MemberDTO member = (MemberDTO) session.getAttribute("user");
		model.addAttribute("member", member);
		model.addAttribute("inquirylist", list);
		model.addAttribute("totalPageNumber", totalPageNumber);
		return "thymeleaf/inq/inquiryboard";
	}

	// 관리자
	@GetMapping("/inquiryboard/admin")
	public String inqboard_admin(Model model, String pageNo, HttpSession session) {
		PageDTO page = service.findAll(Integer.parseInt(pageNo));
		List<InquiryDTO> list = page.getList();
		int totalPageNumber = page.getTotalPageNumber();
		MemberDTO member = (MemberDTO) session.getAttribute("user");
		model.addAttribute("member", member);
		model.addAttribute("inquirylist", list);
		model.addAttribute("totalPageNumber", totalPageNumber);
		return "thymeleaf/inq/inquiryboard_admin";
	}

	@GetMapping("/inquiry/search")
	public String searchinq(Model model, @RequestParam("pageNo") String pageNo, @RequestParam("keyword") String keyword,
			HttpSession session) {
		System.out.println(keyword);
		PageDTO page = service.searchinq(Integer.parseInt(pageNo), keyword);
		List<InquiryDTO> list = page.getList();
		int totalPageNumber = page.getTotalPageNumber();
		MemberDTO member = (MemberDTO) session.getAttribute("user");
		model.addAttribute("member", member);
		model.addAttribute("inquirylist", list);
		model.addAttribute("totalPageNumber", totalPageNumber);
		model.addAttribute("keyword", keyword);
		return "thymeleaf/inq/inquiryboard";
	}
	
	@GetMapping("/inquiry/search/admin")
	public String searchinq_admin(Model model, @RequestParam("pageNo") String pageNo, @RequestParam("keyword") String keyword,
			HttpSession session) {
		System.out.println(keyword);
		PageDTO page = service.searchinq(Integer.parseInt(pageNo), keyword);
		List<InquiryDTO> list = page.getList();
		int totalPageNumber = page.getTotalPageNumber();
		MemberDTO member = (MemberDTO) session.getAttribute("user");
		model.addAttribute("member", member);
		model.addAttribute("inquirylist", list);
		model.addAttribute("totalPageNumber", totalPageNumber);
		model.addAttribute("keyword", keyword);
		return "thymeleaf/inq/inquiryboard_admin";
	}

}
