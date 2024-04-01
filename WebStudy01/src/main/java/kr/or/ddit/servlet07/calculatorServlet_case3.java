package kr.or.ddit.servlet07;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.OperatorType;
import kr.or.ddit.vo.CalculatorVO;

/**
 * 응답을 JSON으로 전송하기 위해, 직렬화=(Mashalling, Serailization) 필요함.
 */
@WebServlet("/07/case3/calculator.do")
public class calculatorServlet_case3 extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int status = validate(req);
		
		String accept = req.getHeader("accept"); // req header에 있는 accept를 가지고 오는 것
		
		if(status == HttpServletResponse.SC_OK) { // ok는 200
			String view = null;
			if(accept.contains("json")) {
				view = "/jsonView.do";
			} else {
				view = "/WEB-INF/views/07/calculateView.jsp";
				
			}
			req.getRequestDispatcher(view).forward(req, resp);
		} else {
			resp.sendError(status); // 200이 아니면 status에 담겨있는 에러를 보내줌
		}
	}

	private int validate(HttpServletRequest req) { // 값들을 가지고 오는 메서드
		String left = req.getParameter("left");
		String right = req.getParameter("right");
		String operator = req.getParameter("operator");
		int status = HttpServletResponse.SC_OK;

		try { // 값들을 저장하면서 Double.parseDouble를 사용해서 예외가 발생하면 캐치문으로 넘김
			double leftOp = Double.parseDouble(left);
			double rightOp = Double.parseDouble(right);
			OperatorType operatorType = OperatorType.valueOf(operator); // 주어진 문자열과 일치하는 enum상수 반환

			CalculatorVO calVO = new CalculatorVO(leftOp, rightOp, operatorType); // calVO에 한번에 저장하기 위함
			req.setAttribute("calculator", calVO);
		} catch (Exception e) {
			status = HttpServletResponse.SC_BAD_REQUEST; // 예외가 발생하면 여기로 발생시킴
		}
		return status;
	}
}
