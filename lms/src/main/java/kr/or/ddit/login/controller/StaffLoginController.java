package kr.or.ddit.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login/loginProcess/stf.do")
public class StaffLoginController {
	
	@PostMapping
	public String login(@RequestParam String username, @RequestParam String password, Model model) {
		
//		String message = null;
//		String logicalViewName = null;
		
//		if(proInfo!=null) {
//			req.setAttribute("proInfo",proInfo);
//			logicalViewName = "main";
//		}else {
//			message="등록되지 않은 아이디입니다.";
//			session.setAttribute("message",message);
//		logicalViewName = "redirect:login/loginForm.jsp";
//		}
		
		model.addAttribute("stfId", username);
		return "login/loginStf"; //교직원 메인 페이지 

	}
}
