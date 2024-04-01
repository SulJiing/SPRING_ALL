package kr.or.ddit.address.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.address.service.AddressService;
import kr.or.ddit.address.service.AddressServiceImpl;
import kr.or.ddit.vo.AddressVO;

@WebServlet ("/address/address.do")
public class AddressFormControllerServlet extends HttpServlet{
	AddressService service = new AddressServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		String param = req.getParameter("");
		
		String logicalViewName = "address/address";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
	}
}
