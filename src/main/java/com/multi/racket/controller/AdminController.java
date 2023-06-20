package com.multi.racket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	@PostMapping("/admin/update")
    public String updateStadiumStatus(@RequestParam("stadiumStatus") StadiumDTO stadium) {
        service.update(stadium);
        return "redirect:/main";  // 등록 후 페이지 리디렉션 설정
    }
}
