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
import com.multi.racket.domain.MemberDTO;
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
	public String inqread(int inquiryNo, Model model, HttpSession session) {
		InquiryDTO inquiry = service.read(inquiryNo);
		model.addAttribute("inquiry", inquiry);	
		MemberDTO member = (MemberDTO) session.getAttribute("user");
		model.addAttribute("member", member);	
		return "thymeleaf/inq/inquiryread";
	}
	
	@GetMapping("/inquirywrite")
	public String inqwrite(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
		MemberDTO member = (MemberDTO)session.getAttribute("user");
		model.addAttribute("member",member);
		return "thymeleaf/inq/inquirywrite";
	}
	
	@PostMapping("/inquiry_write")
	public String write(InquiryDTO inquiry) {
		service.insert(inquiry);
		return "redirect:/inquiryboard?pageNo=0";
	}
	
	@GetMapping("/replypage")
	public String replypage(HttpSession session, Model model, InquiryDTO inquiry) {
		if (session.getAttribute("user") == null) {
            return "redirect:/login";
        } else {
        	MemberDTO member = (MemberDTO) session.getAttribute("user");
        	if (member.getMemberId()!="admin") {
        		model.addAttribute("replyalert", true);
        	}
        }
		return "redirect:/inquiryread?inquiryNo="+inquiry.getInquiryNo()+"&state=READ";
	}
	
	@PostMapping("/inquiry/reply")
	public String inqreply(InquiryDTO reply, HttpSession session) {
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
	
	@RequestMapping("/admin_user")
	public String user() {
		return "thymeleaf/inq/admin_user";
	}
	
	@RequestMapping("/admin_register")
	public String register() {
		return "thymeleaf/inq/admin_register";
	}	
		
}
