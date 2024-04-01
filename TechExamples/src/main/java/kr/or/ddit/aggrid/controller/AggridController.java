package kr.or.ddit.aggrid.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/aggrid")
public class AggridController {
	@Inject
	private MemberService memberService;
	
	@Inject
	private ProdService prodService;
	
	@RequestMapping("case1")
	public String case1UI() {
		return "aggrid/case1";
	}
	
	@RequestMapping("case1/json")
	@ResponseBody
	public List<MemberVO> case1Json(){
		// 페이징 처리를 하지 않기 때문에 PaginationInfo 는 더미 객체를 생성하고 전달함.
		return memberService.retrieveMemberList(new PaginationInfo());
	}	
	@RequestMapping("case2")
	public String case2UI() {
		return "aggrid/case2";
	}
	
	@RequestMapping("case2/json")
	@ResponseBody
	public List<ProdVO> case2Json(){
		// 페이징 처리를 하지 않기 때문에 PaginationInfo 는 더미 객체를 생성하고 전달함.
		return prodService.retrieveProdList(new PaginationInfo());
	}
	
}
