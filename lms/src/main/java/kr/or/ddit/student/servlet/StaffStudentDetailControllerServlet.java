package kr.or.ddit.student.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.student.service.StudentOthersService;
import kr.or.ddit.student.service.StudentOthersServiceImpl;

@WebServlet("/staff/staffStudentDetail.do")
public class StaffStudentDetailControllerServlet extends HttpServlet{
	
	private StudentOthersService othersService = new StudentOthersServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String stdNo = req.getParameter("stdNo");
		
		Map<String, Object> studentMap = othersService.retrieveStudent(stdNo);
		List<Map<String, Object>> clazzList = othersService.retrieveStudentClazz(stdNo);
		
		HttpSession session = req.getSession();
		session.setAttribute("studentMap", studentMap);
		session.setAttribute("clazzList", clazzList);
		
		String logicalViewName = "staff/student/staffStudentDetail";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
		
		
	}

}
