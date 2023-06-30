package com.multi.racket.stadium;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.racket.domain.StadiumDTO;

import com.multi.racket.domain.StadiumcourtDTO;
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
    public String stadium(Model model, String pageNo) {
//		if (pageNo == null) {
//			pageNo = "0";
//		}
//		List<StadiumDTO> list = service.stadiumList(Integer.parseInt(pageNo));
//		model.addAttribute("stadiumList",list);
//		System.out.println("=======================================================");
//		System.out.println(list);
		List<StadiumDTO> list = service.find_grant();
		List<StadiumFileDTO> filelist = new ArrayList<>();
		if (pageNo == null) {
			pageNo = "0";
		}
		for (StadiumDTO stadium : list) {
			List<StadiumFileDTO> files = fileservice.find_file_grant(stadium);
			if (files != null && !files.isEmpty()) {
				filelist.addAll(files);
			}
		}
		model.addAttribute("stadiumlist", list);
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
//		Optional<StadiumDTO> stadium = service.getStadium(stadium_no);
//		model.addAttribute("stadium", stadium);
//		System.out.println("optional 넘어왔나" + stadium);
//		model.addAttribute("state", state);
        return "thymeleaf/stadium/stadiumDetail";
    }
	
	// 예약하기 상세조회
	@GetMapping("/stadium/read/{stadiumNo}")
	public String getStadiumDetail(@PathVariable int stadiumNo, Model model) {
		StadiumDTO stadium = service.getStadium(stadiumNo);
		int courtCount = service.getStadiumCourtCount(stadiumNo); // 코트 갯수 조회
		model.addAttribute("stadium", stadium);
	    model.addAttribute("participantCount", courtCount);
	    System.out.println("controller: "+stadium.getCourts());
		return "thymeleaf/stadium/stadiumDetail";
	}

	
	@RequestMapping("/court")
    public String court() {
        return "thymeleaf/stadium/court";
    }
}