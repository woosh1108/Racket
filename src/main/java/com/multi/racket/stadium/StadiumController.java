package com.multi.racket.stadium;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumcourtDTO;

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
//	@RequestMapping("/stadium/stadiumDetail")
//    public String stadiumDetail(Model model, int stadium_no, String state) {
//		Optional<StadiumDTO> stadium = service.getStadium(stadium_no);
//		model.addAttribute("stadium", stadium);
//		System.out.println("optional 넘어왔나" + stadium);
//		model.addAttribute("state", state);
//        return "thymeleaf/stadium/stadiumDetail";
//    }
	
	// 구장 목록보기
	@RequestMapping("/stadium/stadiumlist")
	public String stadiumlist(Model model, @RequestParam(defaultValue = "0") int pageNo) {
		Page<StadiumDTO> stadiumlistPage = service.stadiumlist(pageNo);
        List<StadiumDTO> stadiumlist = stadiumlistPage.getContent();

        model.addAttribute("stadiumlist", stadiumlist);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", stadiumlistPage.getTotalPages());

        return "thymeleaf/stadium/stadium";
	}
	
	// 구장 상세조회
    @GetMapping("/stadium/read/{stadiumNo}")
    public String getStadiumDetail(@PathVariable int stadiumNo, Model model, HttpServletRequest request) {
    	HttpSession sessions = request.getSession(false); // 세션이 존재하지 않을 경우 null 반환
	    if (sessions == null || sessions.getAttribute("user") == null) {
	        return "redirect:/login";
	    }
	    
//	    System.out.println("controller 코드번호: "+stadiumNo);
        StadiumDTO stadium = service.getStadium(stadiumNo);
        int courtCount = service.getStadiumCourtCount(stadiumNo); // 코트 갯수 조회
        List<StadiumcourtDTO> courtlist = service.getCourtslistByStadiumNo(stadiumNo);
        model.addAttribute("stadium", stadium);
        model.addAttribute("courtlist", courtlist);
        model.addAttribute("participantCount", courtCount);
//	    System.out.println("controller stadium: "+stadium);
//	    System.out.println("controller courtlist: "+courtlist);
//	    System.out.println("controller 코트갯수: "+courtCount);
        return "thymeleaf/stadium/stadiumDetail";
    }
	
	@GetMapping("/stadiums/search")
    public String searchStadiums(@RequestParam String type, @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int pageNo, Model model) {
        int pageSize = 10; // 페이지당 표시할 데이터 수

        Page<StadiumDTO> stadiumPage = service.searchStadiums(type, keyword, pageNo, pageSize);
        List<StadiumDTO> stadiumlist = stadiumPage.getContent();

        model.addAttribute("stadiumlist", stadiumlist);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", stadiumPage.getTotalPages());

        return "thymeleaf/stadium/stadium";
    }
	
	@RequestMapping("/court")
    public String court() {
        return "thymeleaf/stadium/court";
    }
	
}