package com.multi.racket.inquiry;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.multi.racket.domain.InquiryDTO;
@Controller
public class InquiryPageController {
	InquiryPageService service;
	
	@Autowired
	public InquiryPageController(InquiryPageService service) {
		super();
		this.service = service;
	}


	@GetMapping("/inquiryboard")
	public String inqboard(Model model, String pageNo) {
		List<InquiryDTO> list = service.findAll(Integer.parseInt(pageNo));
		model.addAttribute("inquirylist",list);
		System.out.println(list);
		return "thymeleaf/inq/inquiryboard";
	}
	
}
