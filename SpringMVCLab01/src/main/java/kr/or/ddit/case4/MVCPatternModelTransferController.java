package kr.or.ddit.case4;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/case4")
@Slf4j
public class MVCPatternModelTransferController {

	@GetMapping("receiveContent1")
	public String handler1_html(Model model) {
		String info = "이미 가공된 정보";
		model.addAttribute("info",info);
		return "case4/modelView1";
	}
	
	@GetMapping(value = "receiveContent1", produces = MediaType.APPLICATION_JSON_VALUE)
	public String handler1_json(Model model) {
		String info = "이미 가공된 정보";
		model.addAttribute("info",info);
		return "jsonView";
	}
	
	@GetMapping("receiveContent2")
	public ModelAndView handler2() {
		String info = "이미 가공된 정보";
		ModelAndView mav = new ModelAndView();
		mav.addObject("info",info);
		mav.setViewName("case4/modelView1");
		return mav;
	}
}
