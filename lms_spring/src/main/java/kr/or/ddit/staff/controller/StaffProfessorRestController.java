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

import kr.or.ddit.professor.service.ProfessorService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.ProfessorVO;
import lombok.extern.slf4j.Slf4j;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.professor.dao.ProfessorDAO;

@Slf4j
@Controller
@RequestMapping("/staff")
public class StaffProfessorRestController {
	
	@Inject
	ProfessorService pService;

	@ModelAttribute("professor")
	private ProfessorVO professor() {
		return new ProfessorVO();
	}
	
	//상세조회
	@GetMapping("professorView/{proNo}")
	public String professorView(@PathVariable String proNo, Model model) {
		ProfessorVO professor = pService.retrieveProfessor(proNo);
		model.addAttribute("professor", professor);
		return "staff:staff/professorView";
	}
	
	//수정폼
	@GetMapping("professorUpdate/{proNo}")
	public String professorUpdate(@PathVariable String proNo, Model model) {
		ProfessorVO professor = pService.retrieveProfessor(proNo);
		model.addAttribute("professor", professor);
		return "staff:staff/professorUpdate";
	}

	//수정
	@PostMapping("professorUpdate/{proNo}")
	public String professorUpdateProcess(
			@PathVariable String proNo,
			@Valid @ModelAttribute ProfessorVO professor
			) {
		ServiceResult result = pService.updateProfessor(professor);
		switch (result) {
		case OK:
			return "redirect:/staff/professorView/"+proNo;
		default:
			return "redirect:/staff/professorUpdate/"+proNo;
		}
	}
	
	//등록폼
	@GetMapping("professorForm")
	public String professorForm() {
		return "staff:staff/professorForm";
	}
	
	//등록
	@PostMapping("professorForm")
	public String professorinsertProcess(
			@Validated(Default.class) @ModelAttribute("professor") ProfessorVO professor,
			BindingResult errors) {
		if(!errors.hasErrors()) {
			ServiceResult result = pService.insertProfessor(professor);
			switch (result) {
			case OK:
				return "redirect:/staff/professorView/"+professor.getProNo();
			default:
				return "staff:staff/professorForm";
			}
		}else {
			return "staff:staff/professorForm";
		}
	}
	
	
	
	
	
}
