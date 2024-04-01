package kr.or.ddit.case8;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.case8.vo.InfoVO;

@Controller
@RequestMapping("/case8")
public class JsonResponseController {
	
	@GetMapping("jsonResp1")
	public String handler1(Model model) {
		String info = "동적으로 생성한 모델";
		model.addAttribute("info", info);
		return "jsonView";
	}
	
	@GetMapping("jsonResp2")
	// Accept : application/json 지정하면 나옴
	public void handler2(Model model) {
		String info = "동적으로 생성한 모델";
		model.addAttribute("info", info);
//		return "case8/jsonResp2";
	}
	
	@GetMapping(value="jsonResp3", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String handler3() {
		String info = "동적으로 생성한 모델";
		return info;
	}
	
	@GetMapping(value="jsonResp4", produces = "application/json;charset=UTF-8")
	@ResponseBody // return해준 값이 response로 나간다는 것을 명시
	public Map<String, Object> handler4() {
		String info1 = "동적으로 생성한 모델";
		String info2 = "동적으로 생성한 모델";
		Map<String, Object> jsonObj = new HashMap<>();
		jsonObj.put("info1",info1);
		jsonObj.put("info2",info2);
		return jsonObj;
	}
	
	@GetMapping(value="jsonResp5", produces = "application/json;charset=UTF-8")
	public String handler5(Model model) {
		String info1 = "동적으로 생성한 모델";
		String info2 = "동적으로 생성한 모델";
		model.addAttribute("info1",info1);
		model.addAttribute("info2",info2);
		return "jsonView";
	}
	
	@GetMapping(value="jsonResp6")
	public String handler6(Model model) {
		String info1 = "동적으로 생성한 모델";
		String info2 = "동적으로 생성한 모델";
		
		InfoVO vo = new InfoVO();
		vo.setInfo1(info1);
		vo.setInfo2(info2);
		
		model.addAttribute("info",vo);
		return "jsonView";
	}
	
	@GetMapping(value="jsonResp7")
	@ResponseBody
	public InfoVO handler7() {
		String info1 = "동적으로 생성한 모델";
		String info2 = "동적으로 생성한 모델";
		
		InfoVO vo = new InfoVO();
		vo.setInfo1(info1);
		vo.setInfo2(info2);
		
		return vo;
	}
}