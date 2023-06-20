package com.multi.racket.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/inquirywrite")
	public String inqwrite(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
		return "thymeleaf/inq/inquirywrite";
	}
	
	@PostMapping("/inquiry_write")
	public String write(InquiryDTO inquiry) {
		service.insert(inquiry);
		return "redirect:/inquiryboard?pageNo=0";
	}
	
	@PostMapping("/inquiry/reply")
	public String inqreply(InquiryDTO reply) {
		service.reply(reply);
		return "redirect:/inquiryread?inquiryNo="+reply.getInquiryNo()+"&state=READ";
	}
	
	@GetMapping("/inquiry/delete")
	public String inqdelete(int inquiryNo) {
		service.delete(inquiryNo);
		return "redirect:/inquiryboard?pageNo=0";
	}
	
	@RequestMapping("/report")
	public String report() {
		return "thymeleaf/inq/report";
	}
	
	
		
}
