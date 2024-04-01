package kr.or.ddit.clazz.servlet;

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

@WebServlet("/student/clazz.do")
public class StudentClazzControllerServlet extends HttpServlet{
	
	StudentOthersService othersService = new StudentOthersServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Map<String, Object> stdInfo = (Map<String, Object>) session.getAttribute("stdInfo");
		
		String stdNo = (String) stdInfo.get("stdNo");
		List<Map<String,Object>> clazzList = othersService.retrieveStudentClazz(stdNo);
		
		req.setAttribute("clazzList", clazzList);
		
		String logicalViewName = "student/clazz";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
		
	}
	
	
}
