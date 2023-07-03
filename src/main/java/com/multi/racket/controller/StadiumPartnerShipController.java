package com.multi.racket.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.multi.racket.common.FileUploadLogicService;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumFileDTO;
import com.multi.racket.stadiumpartnership.StadiumPartnerShipService;

@Controller
public class StadiumPartnerShipController {
	StadiumPartnerShipService service;
	FileUploadLogicService fileservice;
	@Autowired
	public StadiumPartnerShipController(StadiumPartnerShipService service, FileUploadLogicService fileservice) {
		super();
		this.service = service;
		this.fileservice = fileservice;
	}

	@GetMapping("/associate")
	public String associate(HttpServletRequest request) {
		HttpSession sessions = request.getSession(false); // 세션이 존재하지 않을 경우 null 반환
	    if (sessions == null || sessions.getAttribute("user") == null) {
	        return "redirect:/login";
	    }
		return "thymeleaf/signup/associate";
	}
	
	@PostMapping("/associate")
	public String partner_insert(StadiumDTO associate) {
		service.partnership_insert(associate);
		return "thymeleaf/signup/associate";
	}
	@PostMapping("/associate/test")
	public String write(StadiumDTO stadium, List<MultipartFile> stadiumFiles ,HttpSession session) throws IllegalStateException, IOException {
		
//		service.insert(stadium,stadiumfiledtolist);
		service.partnership_insert(stadium);
		List<StadiumFileDTO> stadiumfiledtolist = fileservice.uploadFiles(stadiumFiles,stadium);
		service.file_insert(stadiumfiledtolist);
		System.out.println("stadiumDTO값 : "+stadium);
		return "redirect:/main";//컨트롤러를 요청재지정
	}
	
}
