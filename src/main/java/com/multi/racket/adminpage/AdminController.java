package com.multi.racket.adminpage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.manage.ManageService;

@Controller
public class AdminController {
	ManageService service;
	@Autowired
	public AdminController(ManageService service) {
		super();
		this.service = service;
	}

	@RequestMapping("/admin_user")
	public String user() {
		return "thymeleaf/inq/admin_user";
	}
	
	@RequestMapping("/admin_register")
	public String register(Model model) {
		List<StadiumDTO> list = service.findAll();
		model.addAttribute("stadiumlist",list);
		return "thymeleaf/inq/admin_register";
	}	
}
