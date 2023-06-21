package com.multi.racket.inquiry;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.multi.racket.domain.InquiryDTO;
import com.multi.racket.domain.MemberDTO;
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
		model.addAttribute("member",member);
		model.addAttribute("inquirylist",list);
		model.addAttribute("totalPageNumber", totalPageNumber);
		return "thymeleaf/inq/inquiryboard";
	}
	
}
