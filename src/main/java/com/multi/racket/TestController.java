package com.multi.racket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.multi.racket.associate.AssociateService;
import com.multi.racket.domain.StadiumDTO;

@Controller
public class TestController {
	AssociateService assoservice;
	@Autowired
	public TestController(AssociateService assoservice) {
		super();
		this.assoservice = assoservice;
	}


	@GetMapping("/main")
	public String index_test(Model model) {
		List<StadiumDTO> list = assoservice.findAll();
		model.addAttribute("stadiumlist",list);
		return "thymeleaf/main/mainpage";
	}
	
	
	@GetMapping("/main_intro")
	public String main_intro() {
		return "thymeleaf/main/main_intro";
	}
	
}
