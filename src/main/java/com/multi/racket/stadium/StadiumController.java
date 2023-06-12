package com.multi.racket.stadium;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StadiumController {


	@RequestMapping("/stadium")
    public String stadium(Model model) {
        return "thymeleaf/stadium/stadium";
    }
	
	//나중에 id값으로 들어가게 매핑 변경해야함.
	@RequestMapping("/stadiumdetail")
    public String stadiumdetail() {
        return "thymeleaf/stadium/stadiumDetail";
    }
	
	@RequestMapping("/court")
    public String court() {
        return "thymeleaf/stadium/court";
    }
	
	@RequestMapping("/court")
    public String court() {
        return "thymeleaf/court";
    }
}
