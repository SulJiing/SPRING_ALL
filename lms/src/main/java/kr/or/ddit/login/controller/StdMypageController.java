package kr.or.ddit.login.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.student.service.StudentOthersService;

@Controller
@RequestMapping("/mypage/std.do")
public class StdMypageController {
	
	@Inject
	private StudentOthersService service;
	
	@GetMapping
	protected String myPageS() {
		return "login/mypageStd";
	}
}