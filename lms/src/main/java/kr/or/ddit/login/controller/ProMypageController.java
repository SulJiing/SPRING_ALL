package kr.or.ddit.login.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.professor.service.ProfessorService;


@Controller
@RequestMapping("/mypage/pro.do")
public class ProMypageController {
	
	@Inject
	private ProfessorService service;
	
	@GetMapping
	public String myPage() {
		return "login/mypagePro";
	}
}