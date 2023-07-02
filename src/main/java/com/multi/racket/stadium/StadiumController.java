package com.multi.racket.stadium;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.racket.announcement.AnnouncementDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumFileDTO;
import com.multi.racket.manage.ManageService;
import com.multi.racket.stadiumpartnership.StadiumPartnerShipService;

@Controller
public class StadiumController {
	ManageService service;
	StadiumPartnerShipService fileservice;
	
	
	@Autowired
	public StadiumController(ManageService service, StadiumPartnerShipService fileservice) {
		super();
		this.service = service;
		this.fileservice = fileservice;
	}

	@RequestMapping("/stadium")
    public String stadium(Model model,@RequestParam(defaultValue = "0") String pageNo) {
		
		int pageNumber = Integer.parseInt(pageNo);
		int pageSize = 10; // 페이지 당 공지사항 수
		if (pageNo == null) {
			pageNo = "0"; // 기본 페이지 번호 설정
			pageNumber = Integer.parseInt(pageNo);
		}

		if (pageNumber > service.getTotalPages(pageSize)) {
			pageNumber = 0; // 페이지 번호가 총 페이지 수를 초과하는 경우 첫 페이지로 설정
		}
		
		List<StadiumDTO> stadiumlist = service.stadiumlist(pageNumber);
//		long totalPages = service.getTotalPages(pageSize,stadiumlist.size());
		long totalPages = service.getTotalPages(pageSize);
		List<StadiumFileDTO> filelist = new ArrayList<>();
		for (StadiumDTO stadium : stadiumlist) {
			List<StadiumFileDTO> files = fileservice.find_file_grant(stadium);
			if (files != null && !files.isEmpty()) {
				filelist.addAll(files);
			}
		}
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("stadiumlist", stadiumlist);
		if (!filelist.isEmpty()) {
			model.addAttribute("stadiumfile", filelist);
		}
        return "thymeleaf/stadium/stadium";
    }
	
	//나중에 id값으로 들어가게 매핑 변경해야함.
	@RequestMapping("/stadium/stadiumDetail")
    public String stadiumDetail(Model model, int stadiumNo) {
		StadiumDTO stadium = service.find_stadiumno(stadiumNo);
		List<StadiumFileDTO> stadiumfile = fileservice.find_file(stadiumNo);
		model.addAttribute("stadiumfile",stadiumfile);
		model.addAttribute("stadium",stadium);
        return "thymeleaf/stadium/stadiumDetail";
    }
	
	// 게시글 검색기능
		@RequestMapping("/stadium_search")
		public String search(String data, Model model) {
			List<StadiumDTO> searchlist = service.search(data);
			List<StadiumFileDTO> filelist = new ArrayList<>();
			for (StadiumDTO stadium : searchlist) {
				List<StadiumFileDTO> files = fileservice.find_file_grant(stadium);
				if (files != null && !files.isEmpty()) {
					filelist.addAll(files);
				}
			}
			if (!filelist.isEmpty()) {
				model.addAttribute("stadiumfile", filelist);
			}
			model.addAttribute("searchlist", searchlist);
			return "thymeleaf/stadium/stadium_search";

		}
}