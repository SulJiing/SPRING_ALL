package kr.or.ddit.login.controller;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.student.service.StudentOthersService;

@Controller
@RequestMapping("/login/loginProcess/std.do")
public class StudentLoginController {

	@Inject
	private StudentOthersService service;
	
	@PostMapping
	public String login(@RequestParam String username, Model model, RedirectAttributes redirectAttributes) {

		Map<String, Object> stdInfo = service.retrieveStudent(username);
		
		String message = null;
		String logicalViewName = null;
		
		if(stdInfo!=null) {
			redirectAttributes.addFlashAttribute("stdInfo",stdInfo);
			logicalViewName="login/loginStd";
		}else {
			message="등록되지 않은 아이디입니다.";
			redirectAttributes.addFlashAttribute("message",message);
			logicalViewName = "redirect:/login/loginForm.jsp";
		}
		return logicalViewName;
	}
}