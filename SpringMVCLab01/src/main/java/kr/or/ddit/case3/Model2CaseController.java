package kr.or.ddit.case3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/case3")
@Slf4j
public class Model2CaseController {
	
	@GetMapping("sendRequest1")
	public String handler1() {
		String logicalViewName = "case3/inner1";
		// 어디로 가는지만 설정해주면 됨
		return logicalViewName;
	}

	@GetMapping(value="sendRequest2")
	public String handler2() {
		// 마샬링이 필요하면...
		return "jsonView";
	}
	
	@RequestMapping(value = "sendRequest3", method = RequestMethod.GET, produces = "text/html" )
	public void handler3_html() {
		log.info("========(HTML 요청 처리)========");
	}
	
	@GetMapping(value = "sendRequest3", produces = "application/json")
	public void handler3_json() {
		log.info("========(JSON 요청 처리)========");
	}
}
