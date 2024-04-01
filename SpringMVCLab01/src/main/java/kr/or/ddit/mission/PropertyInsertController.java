package kr.or.ddit.mission;

import javax.validation.groups.Default;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.case7.InsertGroup;
import kr.or.ddit.mission.vo.PropertyVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mission/propView")
public class PropertyInsertController {

	// FormUI를 제공하기 위한 GET 메서드
	@GetMapping
	public void formUI(@ModelAttribute("property") PropertyVO property) {
//		return "mission/propView";
	}
	
	@PostMapping
	public String processFormData(@Validated({InsertGroup.class, Default.class}) @ModelAttribute("property") PropertyVO property, Errors errors, 
			RedirectAttributes redirectAttributes) {
		if(!errors.hasErrors()) {
			log.info("command object : {}");
			String message  = "프로퍼티 등록 성공";
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:/";
		} else {
			return "mission/propView";
		}
	}
} 
