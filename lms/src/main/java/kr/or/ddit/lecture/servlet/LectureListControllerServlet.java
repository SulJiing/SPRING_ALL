package kr.or.ddit.lecture.servlet;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.paging.BootstrapFormBasePaginationRenderer;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.common.paging.PaginationRenderer;
import kr.or.ddit.lecture.service.LectureService;
import kr.or.ddit.vo.LectureVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/lecture/lectureList.do")
public class LectureListControllerServlet{
	
	@Inject
	private	LectureService service;
	
	@GetMapping
	public String list(
		@RequestParam Map<String, Object> detailCondition
		, @RequestParam(name="page", required = false, defaultValue = "1") int currentPage
		, Model model
	) {
		log.info("adsadsadsadsads : {}","adadsadsadsasdasdadsads");
		PaginationInfo paging = new PaginationInfo();
		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(detailCondition); 
		
		List<LectureVO> lectureList = service.retrieveLectureList(paging);
		
		PaginationRenderer renderer = new BootstrapFormBasePaginationRenderer("#searchForm");
		
		String pagingHTML = renderer.renderPagination(paging);
		
		model.addAttribute("pagingHTML", pagingHTML);
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("condition", detailCondition);
		
		return "lecture/lectureList";
	}
}
