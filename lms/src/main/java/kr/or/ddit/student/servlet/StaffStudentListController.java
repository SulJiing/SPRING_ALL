package kr.or.ddit.student.servlet;

import java.util.List;
import java.util.Map;

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
import kr.or.ddit.student.service.StudentOthersService;

@Controller
@RequestMapping("/staff/staffStudentList.do")
public class StaffStudentListController {
	
	@Inject
	private StudentOthersService othersService;
	
	@GetMapping
	public String list(@ModelAttribute("paging") PaginationInfo paging
			, @RequestParam(name="page", required = false, defaultValue = "1") int currentPage
			, Model model) {
		
		paging.setCurrentPage(currentPage);
		
		List<Map<String,Object>> studentList = othersService.retrieveStudentList(paging);
		
		PaginationRenderer renderer = new BootstrapFormBasePaginationRenderer("#searchForm");
		
		String pagingHTML = renderer.renderPagination(paging);
		
		model.addAttribute("studentList", studentList);
		model.addAttribute("pagingHTML", pagingHTML);
		
		return "staff/student/staffStudentList";
	}
}