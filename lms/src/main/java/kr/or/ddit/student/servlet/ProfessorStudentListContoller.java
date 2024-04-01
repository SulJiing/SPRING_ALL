package kr.or.ddit.student.servlet;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.clazz.service.ClazzOthersService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClazzVO;

@Controller
@RequestMapping("/professor/studentList.do")
public class ProfessorStudentListContoller {
		
	@Inject
	private ClazzOthersService othersService;
		
	@GetMapping
	public String list(@RequestParam String subCd, RedirectAttributes redirectAttributes) {
		
		List<Map<String, Object>> clazzList = othersService.retrieveStudentClazz(subCd);
		
		redirectAttributes.addFlashAttribute("clazzList", clazzList);
		
		return "professor/studentList";
	}
	
	@PostMapping
	public String select(@ModelAttribute("clazz") ClazzVO clazzVO, RedirectAttributes redirectAttributes) {
		
		ServiceResult result = othersService.modifyClazz(clazzVO);
		
		if(result == ServiceResult.FAIL) {
			String message = "서버 오류";
			redirectAttributes.addFlashAttribute("message", message);
		}
		
		return "/professor/studentList.do?subCd="+clazzVO.getSubCd();
	}
}
