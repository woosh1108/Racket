package com.multi.racket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.manage.ManageService;

@Controller
public class ManageController {
	ManageService service;
	@Autowired
	public ManageController(ManageService service) {
		super();
		this.service = service;
	}

	@RequestMapping("/admin_user")
	public String user(Model model) {
		List<MemberDTO> list = service.findUser();
		model.addAttribute("memberlist",list);
		return "thymeleaf/inq/admin_user";
	}
	
	@RequestMapping("/admin_register")
	public String register(Model model) {
		List<StadiumDTO> list = service.findAll();
		model.addAttribute("stadiumlist",list);
		return "thymeleaf/inq/admin_register";
	}	
	
	@PostMapping("/admin/update")
	public String updateStadiumStatus(@RequestBody List<StadiumDTO> stadiums) {
	    service.update(stadiums);
	    return "redirect:/main";
	}
}
