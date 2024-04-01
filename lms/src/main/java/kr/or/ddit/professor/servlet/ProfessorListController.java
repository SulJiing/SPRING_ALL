package kr.or.ddit.professor.servlet;

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
import kr.or.ddit.vo.ProfessorVO;

@Controller
@RequestMapping("/professor/professorList.do")
public class ProfessorListController {
	
	@Inject
	ProfessorService service;
	
	@GetMapping
	public String list(
		@ModelAttribute("paging") PaginationInfo paging
		, @RequestParam(name="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		 
//		PaginationInfo paging = new PaginationInfo();
		paging.setCurrentPage(currentPage);
//		paging.setSimpleCondition(simpleCondition);
		List<ProfessorVO> professorList = service.retrieveProfessorList(paging);
		
//		PaginationRenderer renderer = new DefaultFormBasePaginationRenderer("#searchForm");
		PaginationRenderer renderer = new BootstrapFormBasePaginationRenderer("#searchForm");
		
		String pagingHTML = renderer.renderPagination(paging);
		
		model.addAttribute("professorList", professorList);
		model.addAttribute("pagingHTML", pagingHTML);
		
		return "professor/professorList";
	}
}