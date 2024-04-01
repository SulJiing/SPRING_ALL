package kr.or.ddit.case5;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/case5")
@Slf4j
public class FlowControlCaseController {

	@GetMapping("start1")
	public String handler1_start(Model model) {
		LocalDateTime now = LocalDateTime.now();
		model.addAttribute("now",now);
		// 다른 모델에게 forward로 보내고 싶을 때 구조
		return "forward:/case5/dest1";
	}

	@GetMapping("dest1")
	public String handler1_dest(@RequestAttribute(name = "now", required = false) LocalDateTime now) {
		log.info("forward로 전달된 model data : {}",now);
		return "case5/finalView1";
	}
	
	@GetMapping("start2")
	public String handler2_start(RedirectAttributes redirectAttributes) {
		LocalDateTime now = LocalDateTime.now();
		// redirectAttributes => Redirect할 때도 데이터가 넘어가도록 함
		// Session Scope에 저장
		// addFlashAttribute => 데이터가 꺼내지면 알아서 지워짐(session에서는 데이터를 지우는 과정이 중요)
		redirectAttributes.addFlashAttribute("now", now);
		
		// 다른 모델에게 redirect로 보내고 싶을 때 구조 
		// 근데 request에 들어간 데이터는 요청이 나가면 사라짐
		// redirect는 요청이 한번 나갔다 다시 오는 구조
		// 그래서 가져오는 파라미터를 바꿔줘야 함
		return "redirect:/case5/dest2";
	}
	
	@GetMapping("dest2")
	public String handler2_dest(Model model) {
		log.info("redirect로 전달된 model data : {}",model.getAttribute("now"));
		return "case5/finalView2";
	}
}
