package kr.or.ddit.staff.controller;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.groups.Default;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.professor.service.ProfessorService;
import kr.or.ddit.student.service.StudentService;
import kr.or.ddit.vo.ProfessorVO;
import kr.or.ddit.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/staff")
public class StaffStudentRestController {
	
	@Inject
	StudentService sService;

	@ModelAttribute("student")
	private StudentVO student() {
		return new StudentVO();
	}
	
	//상세조회
	@GetMapping("studentView/{stdNo}")
	public String studentView(@PathVariable String stdNo, Model model) {
		StudentVO student = sService.retrieveStudent(stdNo);
		model.addAttribute("student", student);
		return "staff:staff/studentView";
	}
	
	//수정폼
	@GetMapping("studentUpdate/{stdNo}")
	public String studentUpdate(@PathVariable String stdNo, Model model) {
		StudentVO student = sService.retrieveStudent(stdNo);
		model.addAttribute("student", student);
		return "staff:staff/studentUpdate";
	}

	//수정
	@PostMapping("studentUpdate/{stdNo}")
	public String studnetUpdateProcess(
			@PathVariable String stdNo,
			@Valid @ModelAttribute StudentVO student
			) {
		ServiceResult result = sService.updateStudent(student);
		switch (result) {
		case OK:
			return "redirect:/staff/studentView/"+stdNo;
		default:
			return "redirect:/staff/studentUpdate/"+stdNo;
		}
	}
	
	//등록폼
	@GetMapping("studentForm")
	public String studentForm() {
		return "staff:staff/studentForm";
	}
	
	//등록
	@PostMapping("studentForm")
	public String studentinsertProcess(
			@Validated(Default.class) @ModelAttribute("student") StudentVO student,
			BindingResult errors) {
		if(!errors.hasErrors()) {
			ServiceResult result = sService.insertStudent(student);
			switch (result) {
			case OK:
				return "redirect:/staff/studentView/"+student.getStdNo();
			default:
				return "staff:staff/studentForm";
			}
		}else {
			return "staff:staff/studentForm";
		}
	}
	
}
