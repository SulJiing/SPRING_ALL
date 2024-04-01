package kr.or.ddit.professor.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.professor.service.ProfessorService;
import kr.or.ddit.professor.service.ProfessorServiceImpl;
import kr.or.ddit.vo.ProfessorVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/professor/professorSelect.do")
public class ProfessorSelectControllerServlet extends HttpServlet {
	ProfessorService service = new ProfessorServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String proNo = req.getParameter("proNo");
		ProfessorVO professor = service.retrieveProfessor(proNo);
		
		Enumeration<String> parameterNames =  req.getParameterNames();
		Map<String, Object> detailCondition = new LinkedHashMap<>();
		while (parameterNames.hasMoreElements()) {
			String name = (String) parameterNames.nextElement();
			detailCondition.put(name,req.getParameter(name));
		}
		req.setAttribute("condition", detailCondition);
		
		req.getSession().setAttribute("professor", professor);
		req.setAttribute("professor", professor);
		
		String logicalViewName = "professor/professorSelect";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
	}
}
