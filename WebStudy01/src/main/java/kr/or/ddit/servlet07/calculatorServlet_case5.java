package kr.or.ddit.servlet07;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.MediaType;
import kr.or.ddit.enumpkg.OperatorType;
import kr.or.ddit.vo.CalculatorVO;

// model2 구조
@WebServlet("/07/case5/calculator.do")
public class calculatorServlet_case5 extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int status = validate(req);
		
		if(status == HttpServletResponse.SC_OK) { // ok는 200
			// 마샬링, 직렬화
			resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
			CalculatorVO calVO = (CalculatorVO) req.getAttribute("calculator");
		try(
				PrintWriter writer = resp.getWriter();
			){
				new ObjectMapper().writeValue(writer, calVO);
			}
		} else {
			resp.sendError(status); // 200이 아니면 status에 담겨있는 에러를 보내줌
		}
	}

	private int validate(HttpServletRequest req) { // 값들을 가지고 오는 메서드
		int status = HttpServletResponse.SC_OK;
		
		try(
			InputStream is = req.getInputStream();
		) { // 값들을 저장하면서 Double.parseDouble를 사용해서 예외가 발생하면 캐치문으로 넘김
			// 역직렬화, 언마샬링
			ObjectMapper objectMapper = new ObjectMapper();
			CalculatorVO calVO = objectMapper.readValue(is, CalculatorVO.class);
			req.setAttribute("calculator", calVO);
		} catch (Exception e) {
			status = HttpServletResponse.SC_BAD_REQUEST; // 예외가 발생하면 여기로 발생시킴
		}
		return status;
	}
}
