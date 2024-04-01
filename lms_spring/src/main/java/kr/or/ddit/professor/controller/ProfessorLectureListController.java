package kr.or.ddit.professor.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.clazz.service.ClazzService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lecture.service.LectureService;
import kr.or.ddit.professor.service.ProfessorService;
import kr.or.ddit.student.service.StudentService;
import kr.or.ddit.vo.ClazzVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.ProfessorVO;
import kr.or.ddit.vo.StudentVO;
import kr.or.ddit.vo.SubjectVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("professor/lectureListPro")
public class ProfessorLectureListController {
	
	@Inject
	LectureService lService;
	@Inject
	StudentService stService;
	@Inject
	ClazzService cService;
	
	
	@GetMapping
	public String lectureList(HttpSession session, Model model){
		
		ProfessorVO pro = (ProfessorVO) session.getAttribute("professor");
		String proNo = pro.getProNo();
		List<LectureVO> lectureList = lService.retrieveLectureListPro(proNo);
		model.addAttribute("lectureList", lectureList);
		
		return "professor:professor/lectureList";
		
	}
	
	@GetMapping("{subCd}")
	public String lecStudentList(@PathVariable String subCd, String subName, Model model) {
		
		List<StudentVO> studentList = stService.retrieveStudentSubList(subCd);
		model.addAttribute("studentList", studentList);
		
		SubjectVO sub = new SubjectVO();
		sub.setSubCd(subCd);
		sub.setSubName(subName);
		
		model.addAttribute("sub", sub);
		
		log.info("{}",subName);
		
		return "professor:professor/lectureView";
	}
	
	@PostMapping("{subCd}")
	public String lecStudentClazz(
			@PathVariable String subCd,
			@ModelAttribute ClazzVO clazz ,
			String clsScore, String score, 
			RedirectAttributes redirect
	) {
		
		int updateScore = Integer.parseInt(clsScore); //기존 학점
		if(StringUtils.isNumeric(score)) {
			updateScore = Integer.parseInt(score); 			
		}
		clazz.setClsScore(updateScore);
		ServiceResult result = cService.modifyClazz(clazz);
		
		if(result == ServiceResult.FAIL) {
			String message = "서버 오류";
			redirect.addFlashAttribute("message", message);
		}
		
		return "redirect:/professor/lectureListPro/"+subCd;
	}
	
	
}
