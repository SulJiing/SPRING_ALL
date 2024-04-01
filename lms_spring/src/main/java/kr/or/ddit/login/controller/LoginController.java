package kr.or.ddit.login.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.professor.dao.ProfessorDAO;
import kr.or.ddit.student.dao.StudentDAO;
import kr.or.ddit.vo.ProfessorVO;
import kr.or.ddit.vo.StaffVO;
import kr.or.ddit.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class LoginController {
	
	@Inject
	StudentDAO sdao;
	@Inject
	ProfessorDAO pdao;
	
	@GetMapping
	public String loginForm() {
		return "login/loginForm";
	}
	
	@PostMapping
	public String loginProcess(
			String gubun,String id, String pw, 
			RedirectAttributes redirect,
			HttpSession session, 
			Model model) {
		
		log.info("\n\n\ngubun : {}\nid : {}\npw : {}\n\n\n",gubun, id, pw);
		
		switch (gubun) {
		case "student":
			StudentVO stu = sdao.selectStudent(id);
			if(stu == null) {
				redirect.addFlashAttribute("message", "존재하지 않는 ID");
				return "redirect:/";
			}
			model.addAttribute("message", "로그인 성공");
			session.setAttribute("student",stu);
			return "student:student/home";
			
		case "professor":
			ProfessorVO pro = pdao.selectProfessor(id);
			if(pro == null) {
				redirect.addFlashAttribute("message", "존재하지 않는 ID");
				return "redirect:/";
			}
			model.addAttribute("message", "로그인 성공");
			session.setAttribute("professor",pro);
			return "professor:professor/home";

		default:
			StaffVO sta = new StaffVO(id, pw);
			model.addAttribute("message", "로그인 성공");
			session.setAttribute("staff",sta);
			return "staff:staff/home";
		}
		
	}
	
}
