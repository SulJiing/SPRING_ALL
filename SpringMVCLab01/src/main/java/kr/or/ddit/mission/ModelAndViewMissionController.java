package kr.or.ddit.mission;

import java.time.LocalDateTime;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * url : /mission/now.nhn
 * method : GET
 * model : 현재 시각, model명 : now
 * reponse content type : HTML / JSON
 */
@Controller
@RequestMapping("/mission/now.nhn")
public class ModelAndViewMissionController {
	
	@Inject // 주입받는 과정
	private NowGeneratorService service;
	
	@GetMapping(produces = MediaType.TEXT_HTML_VALUE)
	public String html(Model model) {
		LocalDateTime now = service.receiveNow();
//		LocalDateTime now = LocalDateTime.now();
		model.addAttribute("now",now);
		return "mission/resultView";
	}
	
	// produces 얘가 있으면 자동으로 인식해서 negotiation(협상)가 동작함 - 리턴타입을 적지 않아도 됨
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) 
	public String json(Model model) {
		LocalDateTime now = service.receiveNow();
//		LocalDateTime now = LocalDateTime.now();
		model.addAttribute("now",now);
		return "jsonView"; // 있어도 되고 없어도 됨 - 스프링 내부에서 돌아가는 구조
	}
}