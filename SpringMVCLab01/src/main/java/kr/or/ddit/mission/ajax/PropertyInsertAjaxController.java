package kr.or.ddit.mission.ajax;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.mission.vo.PropertyVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mission/ajax/propView")
public class PropertyInsertAjaxController {

	// FormUI를 제공하기 위한 GET 메서드
	@GetMapping
	public void formUI() {
	}

	// fetch
	@PostMapping
	public String processFormData(@Valid @ModelAttribute("property") PropertyVO property, Errors errors, 
			RedirectAttributes redirectAttributes) {
		if(!errors.hasErrors()) {
			log.info("command object : {}");
			String message  = "프로퍼티 등록 성공";
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:/";
		} else {
			return "jsonView";
		}
	}
	
	// fetch
//	consumes -> content-type
//	produces -> accept
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public String processJsonPayload(@Valid @RequestBody PropertyVO property, Errors errors, 
			RedirectAttributes redirectAttributes) {
		if(!errors.hasErrors()) {
			log.info("command object : {}");
			String message  = "프로퍼티 등록 성공";
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:/";
		} else {
			return "jsonView";
		}
	}
	// ajax
	 /* @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> processFormData(@Validated
	@RequestBody PropertyVO property, Errors errors) {
		
		Map<String, String> response = new HashMap<>();
		if(!errors.hasErrors()) {
			response.put("message","등록 성공");
		} else {
			response.put("message","등록 실패");
		}
		return response;
	}*/
}
