package com.multi.racket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.manage.ManageService;

@Controller
public class TestController {
	ManageService service;
	@Autowired
	public TestController(ManageService assoservice) {
		super();
		this.service = assoservice;
	}


	@GetMapping("/main")
	public String index_test(Model model) {
		List<StadiumDTO> list = service.find_grant();
		model.addAttribute("stadiumlist",list);
		return "thymeleaf/main/mainpage";
	}
	
	
	@GetMapping("/main_intro")
	public String main_intro() {
		return "thymeleaf/main/main_intro";
	}
	
}
