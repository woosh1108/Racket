package com.multi.racket.stadium;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.racket.domain.StadiumDTO;

@Controller
public class StadiumController {
	StadiumService service;
	
	@Autowired
	public StadiumController(StadiumService service) {
		super();
		this.service = service;
	}

	@RequestMapping("/stadium")
    public String stadium(Model model, String pageNo) {
		if (pageNo == null) {
			pageNo = "0";
		}
		List<StadiumDTO> list = service.stadiumList(Integer.parseInt(pageNo));
		model.addAttribute("stadiumList",list);
		System.out.println("=======================================================");
		System.out.println(list);
        return "thymeleaf/stadium/stadium";
    }
	
	//나중에 id값으로 들어가게 매핑 변경해야함.
	@RequestMapping("/stadium/stadiumDetail")
    public String stadiumDetail(Model model, int stadium_no, String state) {
		Optional<StadiumDTO> stadium = service.getStadium(stadium_no);
		model.addAttribute("stadium", stadium);
		System.out.println("optional 넘어왔나" + stadium);
		model.addAttribute("state", state);
        return "thymeleaf/stadium/stadiumDetail";
    }
	
	@RequestMapping("/court")
    public String court() {
        return "thymeleaf/stadium/court";
    }
}