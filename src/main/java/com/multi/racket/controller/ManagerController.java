package com.multi.racket.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.manager.BlacklistService;

@Controller
public class ManagerController {
	@Autowired
	private BlacklistService blacklistService;

	// 관리자페이지 - 블랙리스트
	@RequestMapping("/manager/blacklist")
	public String blacklist(Model model, @RequestParam(defaultValue = "0") int pageNo, HttpSession session) {
		MemberDTO adminCheck = (MemberDTO) session.getAttribute("user");
		Page<BlacklistDTO> blacklistPage = blacklistService.blacklist(pageNo);
		List<BlacklistDTO> blacklist = blacklistPage.getContent();

		model.addAttribute("blacklist", blacklist);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", blacklistPage.getTotalPages());

		if (adminCheck != null && adminCheck.getMemberAuth().equals(2)) {

			return "thymeleaf/manager/blacklist";
		} else {

			return "thymeleaf/error/access_denied";
		}
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
			// blacklistService.deleteBlacklist(blacklistNo);
			// memberId와 member_auth 수정
			blacklistService.updateMemberAuthAndDeleteBlacklist(blacklistNo);
		}
		return "redirect:/manager/blacklist";
	}

}
