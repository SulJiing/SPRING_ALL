package kr.or.ddit.professor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("professor/home")
public class ProfessorHomeController {
	
	@GetMapping
	public String home(){
		return "professor:professor/home";
	}
	
	
}
