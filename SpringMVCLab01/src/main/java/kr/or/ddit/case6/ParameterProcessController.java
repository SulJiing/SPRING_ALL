package kr.or.ddit.case6;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.case6.vo.Case6DummyVO;
import kr.or.ddit.case6.vo.CommandObject;
import lombok.extern.slf4j.Slf4j;

/**
 *	url : /case6/parameterProcess
 *	모든 연산의 처리가 완료되면, 연산의 처리 결과는 (GET)핸들러를 통해 fromView에서 출력함
 *	model명: result
 *
 *	post 요청 핸들러 메소드는 2가지 방식으로 구현할 것
 *	낱개의 파라미터를 별개 수신하는 방식과, command object 활용방식
 */
@Slf4j
@Controller
@RequestMapping("/case6/parameterProcess")
public class ParameterProcessController {
	
	@GetMapping
	public String formUI() {
		return "case6/formView";
	}

//	@PostMapping
	public String processEach(@RequestParam(required = true) double left
			, @RequestParam(required = true) double right
			, RedirectAttributes redirectAttributes) {
		double result = left + right;
		redirectAttributes.addFlashAttribute("result", result);
//		흐름을 제어하기 위한 return문
		return "redirect:/case6/parameterProcess";
	}
	
	@PostMapping
	public String processAll(@ModelAttribute("object") CommandObject object, 
			RedirectAttributes redirectAttributes) {
		double result = object.getLeft() + object.getRight();;
		object.setResult(result);
		redirectAttributes.addFlashAttribute("result", object);
		return "redirect:/case6/parameterProcess";
	}
}
