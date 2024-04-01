package kr.or.ddit.others.advice;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.others.dao.OthersDAO;

@ControllerAdvice(basePackages = {"kr.or.ddit.prod", "kr.or.ddit.buyer"})
public class OthersControllerAdvice {
	@Inject
	private OthersDAO dao;
	
	@ModelAttribute("lprodList")
	public List<Map<String, Object>> addLprodList() {
		return dao.selectLprodList();
	}
	
	@ModelAttribute("buyerList")
	public List<Map<String, Object>> addBuyerList() {
		return dao.selectBuyerList();
	}
}










