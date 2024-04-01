package kr.or.ddit.login.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.professor.service.ProfessorService;
import kr.or.ddit.vo.ProfessorVO;

@Controller
@RequestMapping("/login/loginProcess/pro.do")
public class ProfessorLoginController {

	@Inject
	private ProfessorService service;
	
	@PostMapping
	public String login(@RequestParam String username, Model model, RedirectAttributes redirectAttributes) {
		
		ProfessorVO proInfo = service.retrieveProfessor(username);
		
		String message = null;
		String logicalViewName = null;
		
		if(proInfo!=null) {
			redirectAttributes.addFlashAttribute("proInfo",proInfo); //mypage까지의 조회를 위해서
			logicalViewName="login/loginPro";
		}else {
			message="등록되지 않은 아이디입니다.";
			redirectAttributes.addFlashAttribute("message",message);
			logicalViewName = "redirect:/login/loginForm.jsp";
		}
		
		return logicalViewName;
	}

}
