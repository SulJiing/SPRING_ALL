package kr.or.ddit.case2;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case2")
public class HeaderReceiveController {

	@GetMapping("header1")
	// 필요한게 있으면 핸들러메서드의 파라미터로 스프링에게 받으면 됨
	public void handler1(HttpServletRequest request) {
		String accept = request.getHeader("accept");
		log.info("accept : {}",accept);
	}
	
	@GetMapping("header2")
	// 직접 꺼내기
	public void handler2(@RequestHeader("accept") String accept) {
		log.info("accept : {}",accept);
	}
	
	@GetMapping("header3")
	// required = false - 있으면 가져오고 없으면 null 반환, 기본값은 true
	// defaultValue = "0" - 있으면 가져오고 없으면 null 대신 0반환
	public void handler3(@RequestHeader(value="myheader", required = false, defaultValue = "0") int customHeader) {
		log.info("customHeader : {}",customHeader);
	}
	
	@GetMapping("header4")
	public void handler4(@RequestHeader Map<String, String> headers) {
		log.info("headers : {}",headers);
		log.info("accept : {}",headers.get("accept"));
	}
	
	@GetMapping("header5")
	// MultiValueMap<String,String> : value가 내부적으로 list로 관리됨
	public void handler5(@RequestHeader MultiValueMap<String,String> headers) {
		log.info("headers : {}",headers);
		log.info("accept : {}",headers.get("accept"));
	}
	
	@GetMapping("header6")
	public void handler6(@RequestHeader HttpHeaders headers) {
		log.info("headers : {}",headers);
		log.info("accept : {}",headers.getAccept());
	}
	
}
