package kr.or.ddit.student.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.clazz.service.ClazzService;
import kr.or.ddit.common.paging.BootstrapFormBasePaginationRenderer;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.common.paging.PaginationRenderer;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.lecture.service.LectureService;
import kr.or.ddit.vo.ClazzVO;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.StudentVO;

@Controller
@RequestMapping("/student/lectureList")
public class StudentLectureListController {
	
	@Inject
	LectureService lService;
	@Inject
	ClazzService cService;
	
	@GetMapping
	public String lectureList(
			@ModelAttribute("paging") PaginationInfo paging
			, @RequestParam(name="page", required = false, defaultValue = "1") int currentPage // 기본값을 설정하지 않아도 된다면 옵셔널로 해도됨
			, Model model
	) {
		
		paging.setCurrentPage(currentPage);
		List<LectureVO> lectureList = lService.retrieveLectureList(paging);
		
		PaginationRenderer renderer = new BootstrapFormBasePaginationRenderer("#submitForm");
		String pagingHTML = renderer.renderPagination(paging);
		
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("pagingHTML", pagingHTML);
		
		return "student:student/lectureList";
	}
	
	@PostMapping
	public String lectureClazzSubmit(String subCd, HttpSession session, RedirectAttributes redirect) {
		
		StudentVO student = (StudentVO) session.getAttribute("student");
		ClazzVO clazz = new ClazzVO();
		clazz.setSubCd(subCd);
		clazz.setStdNo(student.getStdNo());
		
		ServiceResult result = cService.createClazz(clazz);
		
		switch (result) {
		case OK:
			redirect.addFlashAttribute("message", "수강신청이 완료되었습니다.");
			break;
		case PKDUPLICATED:
			redirect.addFlashAttribute("message", "이미 수강신청을 하였습니다.");
			break;
		default:
			redirect.addFlashAttribute("message", "서버오류");
			break;
		}
		
		return "redirect:/student/lectureList";
	}
	
	
	
	
	
}
