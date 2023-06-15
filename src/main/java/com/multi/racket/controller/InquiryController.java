package com.multi.racket.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.multi.racket.domain.InquiryDTO;
import com.multi.racket.inquiry.InquiryService;
@Controller
public class InquiryController {
	InquiryService service;
	
	@Autowired
	public InquiryController(InquiryService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("/inquiryread")
	public String inqread(int inquiryNo, Model model) {
		InquiryDTO inquiry = service.read(inquiryNo);
		model.addAttribute("inquiry", inquiry);
		return "thymeleaf/inq/inquiryread";
	}
	
	@RequestMapping("/inquirywrite")
	public String inqwrite() {
		return "thymeleaf/inq/inquirywrite";
	}
	
	@PostMapping("/inquiry_write")
	public String write(InquiryDTO inquiry) {
		service.insert(inquiry);
		return "redirect:/inquiryboard?pageNo=0";
	}
	
	@RequestMapping("/report")
	public String report() {
		return "thymeleaf/inq/report";
	}
	
	@RequestMapping("/admin_user")
	public String user() {
		return "thymeleaf/inq/admin_user";
	}
	
	@RequestMapping("/admin_register")
	public String register() {
		return "thymeleaf/inq/admin_register";
	}
		
}
