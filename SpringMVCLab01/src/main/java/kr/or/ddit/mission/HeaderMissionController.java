package kr.or.ddit.mission;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * url : /mission/receiveHeader.nhn 요청처리
 * method : POST 요청으로 제한
 * --> 핸들러 메소드내에서 
 * 		해당 요청에 포함된 body의 content-type을 로그로 출력할 것
 * 		단, 해당 헤더가 없는 경우, 400응답전송
 */

@Slf4j
@Controller
@RequestMapping("/mission")
public class HeaderMissionController {

	@PostMapping("receiveHeader.nhn")
	public void handler(@RequestHeader(value="content-type", required = true) String contentType) {
		log.info("content-type : {}",contentType);
	}
}