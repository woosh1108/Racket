package com.multi.racket;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumFileDTO;
import com.multi.racket.manage.ManageService;
import com.multi.racket.stadiumpartnership.StadiumPartnerShipService;

@Controller
public class TestController {
	ManageService service;
	StadiumPartnerShipService fileservice;

	@Autowired
	public TestController(ManageService service, StadiumPartnerShipService fileservice) {
		super();
		this.service = service;
		this.fileservice = fileservice;
	}
	
	@GetMapping("/main")
    public String index_test(Model model, HttpSession session) {
        MemberDTO adminCheck = (MemberDTO) session.getAttribute("user");
        List<StadiumDTO> list = service.find_grant();
        List<StadiumFileDTO> filelist = new ArrayList<>();

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

        // 관리자는 관리자 메인페이지로 넘어갈 수 있도록.
        if (adminCheck != null && adminCheck.getMemberAuth().equals(2)) {

            return "thymeleaf/main/mainpage_admin";
        } else {

            return "thymeleaf/main/mainpage";
        }
    }

	@GetMapping("/main_intro")
	public String main_intro() {
		return "thymeleaf/main/main_intro";
	}
	//작업중
//	@RequestMapping("/main_search")
//	public String search(Model model) {
//		List<StadiumDTO> list = service.find_grant();
//		List<StadiumFileDTO> filelist = new ArrayList<>();
//
//		for (StadiumDTO stadium : list) {
//			List<StadiumFileDTO> files = fileservice.find_file_grant(stadium);
//			if (files != null && !files.isEmpty()) {
//				filelist.addAll(files);
//			}
//		}
//		model.addAttribute("stadiumlist", list);
//		if (!filelist.isEmpty()) {
//			model.addAttribute("stadiumfile", filelist);
//		}
//		return "thymeleaf/main/mainpage";
//	}
	
	@GetMapping("/main/search")
    public String searchStadiums(@RequestParam String keyword, Model model) {
        List<StadiumDTO> stadiumlist = service.search_name(keyword);
        List<StadiumFileDTO> filelist = new ArrayList<>();
        for (StadiumDTO stadium : stadiumlist) {
			List<StadiumFileDTO> files = fileservice.find_file_grant(stadium);
			if (files != null && !files.isEmpty()) {
				filelist.addAll(files);
			}
		}
        model.addAttribute("stadiumlist", stadiumlist);
        if (!filelist.isEmpty()) {
			model.addAttribute("stadiumfile", filelist);
		}
        return "thymeleaf/main/mainpage";
    }
	
}
