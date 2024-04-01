package kr.or.ddit.message.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet ("/10/messageForm.do")
public class MbtiFormControllerServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String logicalViewName = "10/mbtiForm";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
	}
}
