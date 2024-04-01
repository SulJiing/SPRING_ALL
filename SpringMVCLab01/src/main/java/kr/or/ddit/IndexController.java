package kr.or.ddit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	// POJO (Plain Old Java Object)로 컨트롤러 만들기
	@RequestMapping("/index.do")
	public String indexHandler() {
		return "index";
	}
}