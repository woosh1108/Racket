package com.multi.racket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.multi.racket.domain.AssociateDTO;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.stadiumpartnership.StadiumPartnerShipService;

@Controller
public class StadiumPartnerShipController {
	StadiumPartnerShipService service;
	@Autowired
	public StadiumPartnerShipController(StadiumPartnerShipService service) {
		super();
		this.service = service;
	}
	@GetMapping("/associate")
	public String associate() {
		return "thymeleaf/signup/associate";
	}
	@PostMapping("/associate")
	public String partner_insert(StadiumDTO associate) {
		service.partnership_insert(associate);
		return "thymeleaf/signup/associate";
	}
}
