package kr.or.ddit.staff.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.paging.BootstrapFormBasePaginationRenderer;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.common.paging.PaginationRenderer;
import kr.or.ddit.professor.service.ProfessorService;
import kr.or.ddit.student.service.StudentService;
import kr.or.ddit.vo.ProfessorVO;
import kr.or.ddit.vo.StudentVO;

@Controller
@RequestMapping("/staff/studentList")
public class StaffStudentListController {
	
	@Inject
	StudentService sService;
	
	@GetMapping
	public String studentList(
			@ModelAttribute("paging") PaginationInfo paging
			, @RequestParam(name="page", required = false, defaultValue = "1") int currentPage // 기본값을 설정하지 않아도 된다면 옵셔널로 해도됨
			, Model model
	) {
		
		paging.setCurrentPage(currentPage);
		List<StudentVO> studentList = sService.retrieveStudentList(paging);
		
		PaginationRenderer renderer = new BootstrapFormBasePaginationRenderer("#submitForm");
		String pagingHTML = renderer.renderPagination(paging);
		
		model.addAttribute("studentList", studentList);
		model.addAttribute("pagingHTML", pagingHTML);
		
		return "staff:staff/studentList";
	}
	
}
