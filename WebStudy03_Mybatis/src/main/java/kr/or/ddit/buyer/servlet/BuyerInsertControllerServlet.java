package kr.or.ddit.buyer.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.others.advice.OthersControllerAdvice;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.BuyerVO;

@WebServlet("/buyer/buyerInsert.do")
public class BuyerInsertControllerServlet extends HttpServlet {
	private BuyerService service = new BuyerServiceImpl();
	private OthersControllerAdvice advice = new OthersControllerAdvice();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		advice.addLprodList(req);
		advice.addBuyerList(req);
		String logicalViewName = "buyer/buyerForm";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		advice.addLprodList(req);
		advice.addBuyerList(req);
		
		
		BuyerVO buyer = new BuyerVO();
		req.setAttribute("buyer", buyer);
		
		Map<String, String[]> parameterMap = req.getParameterMap();
		PopulateUtils.populate(buyer, parameterMap);
		
		Map<String,String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(buyer, errors, InsertGroup.class);
		String logicalViewName = null;
		if(valid) {
			ServiceResult result = service.createBuyer(buyer);
			String message = null;
			switch (result) {
			case OK:
				logicalViewName = "redirect:/buyer/buyerView.do?what="+buyer.getBuyerId();
				break;
			default:
				logicalViewName = "buyer/buyerForm";
				message = "바꿔야 돼";
				break;
			}
			req.setAttribute("message", message);
			
		} else {
			logicalViewName = "buyer/buyerForm";
		}
		
		if(logicalViewName.startsWith("redirect:")) {
			String redirectViewPath = req.getContextPath()+logicalViewName.substring("redirect:".length());
			resp.sendRedirect(redirectViewPath);
		} else {
			req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
		}
	}
}

