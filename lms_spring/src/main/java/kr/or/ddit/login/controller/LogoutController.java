package kr.or.ddit.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController {
	
	@PostMapping("/logout")
	protected String logout(HttpServletRequest req) {
		req.getSession().invalidate();
		return "redirect:/";
		
	}
}
