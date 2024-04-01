package kr.or.ddit.staff.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("staff/home")
public class StaffHomeController {
	
	@GetMapping
	public String home(){
		return "staff:staff/home";
	}
	
}
