package kr.or.ddit.fancytree.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.fancytree.AbstractFancytreeNode;
import kr.or.ddit.fancytree.BuyerVOFancytreeNode;
import kr.or.ddit.fancytree.LprodFancytreeNode;
import kr.or.ddit.fancytree.ProdVOFancytreeNode;
import kr.or.ddit.others.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;

@Controller
@RequestMapping("/fancytree/case2")
public class FancytreeLazyLoadingController {
	@Inject
	private OthersDAO othersDAO;
	@Inject
	private BuyerService buyerService;
	@Inject
	private ProdService prodService;
	
	@GetMapping
	public String case2UI() {
		return "fancytree/case2";
	}
	
	@GetMapping("json")
	@ResponseBody
	public List<AbstractFancytreeNode<?>> case2Lprod() {
		 return othersDAO.selectLprodList()
				 		.stream()
				 		.map(LprodFancytreeNode::new)
				 		.collect(Collectors.toList());
	}
	
	@GetMapping("json/{buyerLgu}")
	@ResponseBody
	public List<AbstractFancytreeNode<?>> case2BuyerListPerLprod(@PathVariable Map<String,Object> detailCondition) {
		PaginationInfo paging = new PaginationInfo();
		paging.setDetailCondition(detailCondition);
		return buyerService.retrieveBuyerList(paging)
					 		.stream()
					 		.map(BuyerVOFancytreeNode::new)
					 		.collect(Collectors.toList());
	}
	
	@GetMapping("json/{buyerLgu}/{prodBuyer}")
	@ResponseBody
	public List<AbstractFancytreeNode<?>> case3ProdListPerBuyer(@PathVariable Map<String,Object> detailCondition) {
		PaginationInfo paging = new PaginationInfo();
		paging.setDetailCondition(detailCondition);
		return prodService.retrieveProdList(paging)
					 		.stream()
					 		.map(ProdVOFancytreeNode::new)
					 		.collect(Collectors.toList());
	}
}
