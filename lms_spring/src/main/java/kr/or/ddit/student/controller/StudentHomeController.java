package kr.or.ddit.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("student/home")
public class StudentHomeController {
	
	@GetMapping
	public String home(){
		return "student:student/home";
	}
	
}
