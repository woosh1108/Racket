package com.multi.racket.stadium;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StadiumController {


	@RequestMapping("/stadium")
    public String getTime(Model model) {
        return "thymeleaf/stadium";
    }
	
	//나중에 id값으로 들어가게 매핑 변경해야함.
	@RequestMapping("/stadiumdetail")
    public String stadiumdetail() {
        return "thymeleaf/stadiumDetail";
    }
	
	@RequestMapping("/court")
    public String court() {
        return "thymeleaf/court";
    }
}
