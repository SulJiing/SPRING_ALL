package kr.or.ddit.student.servlet;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.student.service.StudentOthersService;
import kr.or.ddit.student.service.StudentService;
import kr.or.ddit.vo.StudentVO;

@Controller
@RequestMapping("/staff/staffStudentCreate.do")
public class StaffStudentCreateController {
	@Inject
	private StudentOthersService othersService;
	@Inject
	private StudentService service;
	
	@GetMapping
	public String insertForm(RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("studentMap");
		redirectAttributes.addFlashAttribute("clazzList");
		
		return "staff/student/staffStudentCreate";
		
	}
	@PostMapping
	public String insertProcess(@ModelAttribute("student") StudentVO student, Errors error
			, RedirectAttributes redirectAttributes
			, Model model) {
		
		redirectAttributes.addFlashAttribute("student", student);
		
		Map<String, String> errors = new LinkedHashMap<>();
		model.addAttribute("errors", errors);
		String logicalViewName = null;
		
		if(!error.hasErrors()) {
			ServiceResult result = service.createStudent(student);	
			String message = null;
			
			switch(result) {
			case FAIL :
				logicalViewName = "/staff/student/taffStudentCreate";
				message = "서버에 잠시 오류가 있었습니다.";
				break;
			case OK :
				logicalViewName = "redirect:/staff/staffStudentList.do";
				break;
			}
			
		}else {
			logicalViewName = "/staff/student/staffStudentCreate";
		}
		return logicalViewName;
	}
}