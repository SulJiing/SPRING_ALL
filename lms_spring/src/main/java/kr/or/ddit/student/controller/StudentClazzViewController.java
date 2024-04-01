package kr.or.ddit.student.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.clazz.service.ClazzService;
import kr.or.ddit.student.service.StudentService;
import kr.or.ddit.vo.StudentVO;

@Controller
@RequestMapping("/student/classView")
public class StudentClazzViewController {
	
	@Inject
	StudentService sService;
	
	@GetMapping
	public String classView(HttpSession session, Model model) {
		
		StudentVO stu = (StudentVO) session.getAttribute("student");
		StudentVO student = sService.retrieveStudent(stu.getStdNo());
		
		model.addAttribute("clazzList", student.getClazzList());
		
		return "student:student/classView";
	}
	
}
