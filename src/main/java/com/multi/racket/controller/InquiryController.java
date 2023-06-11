package com.multi.racket.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class InquiryController {
	@RequestMapping("/inquiryboard")
	public String inqboard() {
		return "thymeleaf/inq/inquiryboard";
	}
	
	@RequestMapping("/inquirywrite")
	public String inqwrite() {
		return "thymeleaf/inq/inquirywrite";
	}
	
	@RequestMapping("/report")
	public String report() {
		return "thymeleaf/inq/report";
	}
	
	@RequestMapping("/inquiryread")
	public String inqread() {
		return "thymeleaf/inq/inquiryread";
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
