package kr.or.ddit.fancytree.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.BuyerVO;

@Controller
@RequestMapping("/fancytree")
public class FancytreeController {
	@Inject
	private BuyerService buyerService;
	
	@RequestMapping("case1")
	public String case1(Model model) {
		List<BuyerVO> buyerList = buyerService.retrieveBuyerList(new PaginationInfo());
		model.addAttribute("buyerList", buyerList);
		return "fancytree/case1";
	}
}
