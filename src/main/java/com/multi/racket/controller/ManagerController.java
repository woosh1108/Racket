package com.multi.racket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.racket.domain.BlacklistDTO;
import com.multi.racket.manager.BlacklistService;

@Controller
public class ManagerController {
	@Autowired
	private BlacklistService blacklistService;


//	@GetMapping("/manager/blacklists")
//	public String blacklistPage() {
//		return "thymeleaf/manager/blacklists";
//	}
//
//	// 관리자페이지 - 블랙리스트
//	@GetMapping("/manager/blacklist_ajax")
//	public String blacklists(Model model, @RequestParam(defaultValue = "0") int pageNo) {
//		Page<BlacklistDTO> blacklistPage = blacklistService.blacklist(pageNo);
//		List<BlacklistDTO> blacklist = blacklistPage.getContent();
//
//		model.addAttribute("blacklist", blacklist);
//		model.addAttribute("currentPage", pageNo);
//		model.addAttribute("totalPages", blacklistPage.getTotalPages());
//		
//
//		System.out.println("controller blacklist: "+blacklist);
//		System.out.println("controller currentPage: "+pageNo);
//		System.out.println("controller totalPages: "+blacklistPage.getTotalPages());
//
//		// Thymeleaf 템플릿을 사용하지 않고 직접 JSON 형식으로 데이터 반환
//		Gson gson = new Gson();
//		String json = gson.toJson(blacklist);
//		System.out.println("controller json: "+json);
//		return json;
//	}

	
	// 관리자페이지 - 블랙리스트
	@RequestMapping("/manager/blacklist")
	public String blacklist(Model model, @RequestParam(defaultValue = "0") int pageNo) {
		Page<BlacklistDTO> blacklistPage = blacklistService.blacklist(pageNo);
        List<BlacklistDTO> blacklist = blacklistPage.getContent();

        model.addAttribute("blacklist", blacklist);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", blacklistPage.getTotalPages());

        return "thymeleaf/manager/blacklist";
	}
	
	@GetMapping("/manager/get-reason")
	@ResponseBody
	public String getBlacklistReason(@RequestParam("blacklistNo") int blacklistNo) {
	    String reason = blacklistService.getBlacklistReason(blacklistNo);
	    System.out.println(reason);
	    return reason;
	}


	@PostMapping("/manager/blacklist/delete")
	public String deleteSelectedBlacklist(@RequestParam("blacklistNo") List<Integer> blacklistNoList) {
		for (int blacklistNo : blacklistNoList) {
			System.out.println("controller: " + blacklistNo);
			blacklistService.updateMemberAuthAndDeleteBlacklist(blacklistNo);
		}
		return "redirect:/manager/blacklist";
	}

}
