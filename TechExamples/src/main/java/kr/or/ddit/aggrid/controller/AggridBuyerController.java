package kr.or.ddit.aggrid.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.vo.BuyerVO;

@Controller
@RequestMapping("/aggrid/case3")
public class AggridBuyerController {
	
	@Inject
	private BuyerService buyerService;
	
	@GetMapping
	public String case1UI() {
		return "aggrid/case3";
	}
	
	@GetMapping(value="json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<BuyerVO> case3Json(){
		// 페이징 처리를 하지 않기 때문에 PaginationInfo 는 더미 객체를 생성하고 전달함.
		return buyerService.retrieveBuyerList(new PaginationInfo());
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BuyerVO case3Update(@RequestBody BuyerVO buyer){
		buyerService.modifyBuyer(buyer);
		return buyer;
	}
}
