package com.multi.racket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	@GetMapping("/main")
	public String index() {
		return "main";
	}
}
