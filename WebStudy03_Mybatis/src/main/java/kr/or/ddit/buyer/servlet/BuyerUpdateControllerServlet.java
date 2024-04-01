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
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;

@WebServlet("/buyer/buyerUpdate.do")
public class BuyerUpdateControllerServlet extends HttpServlet {
	
	private BuyerService service = new BuyerServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String buyerId = req.getParameter("what");
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		
		req.setAttribute("buyer", buyer);
		
		String logicalViewName = "buyer/buyerEdit";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 수정실패 후 데이터 다시 넘겨줌
		BuyerVO buyer = new BuyerVO();
		req.setAttribute("buyer", buyer);
		
		// 파라미터를 Map으로 넘겨서 키값과 벨류로 만듦
		Map<String, String[]> parameterMap = req.getParameterMap();
		PopulateUtils.populate(buyer, parameterMap);
//		System.out.println("asd"+buyer.getBuyer());
		// 에러객체를 만들고 저장해둠
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		// 검증처리
		boolean valid = ValidateUtils.validate(buyer, errors, UpdateGroup.class);
		
		String logicalViewName = null;
		if(valid) {
			ServiceResult result = service.modifyBuyer(buyer);
			String message = null;
			switch(result) {
			case OK:
				logicalViewName = "redirect:/buyer/buyerView.do?what="+buyer.getBuyerId();
				break;
			default:
				logicalViewName = "/buyer/buyerEdit";
				message = "다시 입력 바람";
				break;
			}
			req.setAttribute("message", message);
		}
		else {
			logicalViewName = "/buyer/buyerEdit";
		}
		if (logicalViewName.startsWith("redirect:")) {
			String redirectViewPath = req.getContextPath() + logicalViewName.substring("redirect:".length());
			resp.sendRedirect(redirectViewPath);
		} else {
			req.getRequestDispatcher("/" + logicalViewName + ".miles").forward(req, resp);
		}
	}
}
