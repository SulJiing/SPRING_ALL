package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.paging.BootstrapFormBasePaginationRenderer;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.common.paging.PaginationRenderer;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/prod")
@Slf4j
public class ProdRetrieveController{
	@Inject
	private ProdService service;
	@PostConstruct
	public void init() {
		log.info("주입된 service : {}, proxy 여부 : {}", service.getClass().getName(), AopUtils.isAopProxy(service));
	}
	
	@GetMapping("prodList.do")
	public String list(
		@RequestParam Map<String, Object> detailCondition
		, @RequestParam(name="page", required = false, defaultValue = "1") int currentPage
		, Model model
	){
		PaginationInfo paging = new PaginationInfo();
		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(detailCondition);
		
		List<ProdVO> prodList = service.retrieveProdList(paging);
		
		
		PaginationRenderer renderer = new BootstrapFormBasePaginationRenderer("#searchForm");
		
		String pagingHTML = renderer.renderPagination(paging);
		
		model.addAttribute("prodList", prodList);
		model.addAttribute("pagingHTML", pagingHTML);
		model.addAttribute("condition", detailCondition);
		
		return "prod/prodList";
		
	}
	
	@GetMapping("prodView.do")
	public String prodOne(@RequestParam("what") String prodId, Model model) {
		ProdVO prod = service.retrieveProd(prodId);
		model.addAttribute("prod", prod);
		
		return "prod/prodView";
	}
}










