package kr.or.ddit.fullcalendar.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.fullcalendar.AbstractEvent;
import kr.or.ddit.fullcalendar.MemberVOEvent;
import kr.or.ddit.fullcalendar.ProdVOEvent;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/fullcalendar")
public class FullCalendarController {
	@Inject
	private MemberService memberService;
	
	@Inject
	private ProdService prodService;
	
	@RequestMapping("case1")
	public String case1UI() {
		return "fullcalendar/case1";
	}
	
	@RequestMapping("case1/json")
	@ResponseBody
	public List<AbstractEvent<?>> case1Json(){
		// 페이징 처리를 하지 않기 때문에 PaginationInfo 는 더미 객체를 생성하고 전달함.
		List<MemberVO> memberList = memberService.retrieveMemberList(new PaginationInfo());
		return memberList.stream()
				.filter(m->m.getMemBir()!=null)
				.map(MemberVOEvent::new)
				.collect(Collectors.toList());
	}
	
	
	@RequestMapping("case2")
	public String case2UI() {
		return "fullcalendar/case2";
	}
	
	@RequestMapping("case2/json")
	@ResponseBody
	public List<AbstractEvent<?>> case2Json(){
		// 페이징 처리를 하지 않기 때문에 PaginationInfo 는 더미 객체를 생성하고 전달함.
		List<ProdVO> prodList = prodService.retrieveProdList(new PaginationInfo());
		return prodList.stream()
						.filter(p->p.getProdInsdate()!=null)
						.map(ProdVOEvent::new)
						.collect(Collectors.toList());
	}
	
}
