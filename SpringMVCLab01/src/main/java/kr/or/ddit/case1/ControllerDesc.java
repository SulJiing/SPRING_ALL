package kr.or.ddit.case1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case1/request1.do")
public class ControllerDesc {
	
//	// GET, POST anyway 모두 다 가능함
//	@RequestMapping("/case1/request1.do")
// 	class에서 사용되었기 때문에 계층구조가 이미 적용됨 (/제거)
//	value="request1.do",
	
	@RequestMapping( method = RequestMethod.GET)
	// @RequestMapping과 method 합친게 @GetMapping
	@GetMapping
	public void handler1() {
		log.info("handler method 1번 동작(GET)");
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@PostMapping
	public void handler2() {
		log.info("handler method 2번 동작(POST)");
	}
	
	// 위에 등록된 GET과 POST요청을 제외한 모든 요청을 받아들임
	@RequestMapping
	public void handler3() {
		log.info("handler method 3번 동작(others...)");
	}
}