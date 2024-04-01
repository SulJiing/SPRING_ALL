package kr.or.ddit.professor.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.professor.service.ProfessorService;
import kr.or.ddit.professor.service.ProfessorServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.vo.ProfessorVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/professor/professorInsert.do")
public class ProfessorInsertControllerServlet extends HttpServlet {
	ProfessorService service = new ProfessorServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String logicalViewName = "professor/professorForm";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		ProfessorVO professor = new ProfessorVO();
		
		req.setAttribute("professor", professor);
		
		Map<String,String[]> parameterMap = req.getParameterMap();
		
		PopulateUtils.populate(professor, parameterMap);
		log.info("===>{}",professor);
		
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(professor,errors);
		
		String logicalViewName = null;
		
		if(valid) {
			ServiceResult result = service.createProfessor(professor);
			String message = null;
			switch (result) {
			case OK:
				logicalViewName = "redirect:/";
				break;
			case FAIL:
				logicalViewName = "professor/professorForm";
				break;
			default:
				logicalViewName = "professor/professorForm";
				message = "교수번호, 바꿔야 돼";
				break;
			}
			req.setAttribute("message", message);
		} else {
			logicalViewName = "professor/professorForm";
		}
		
		if(logicalViewName.startsWith("redirect:")) {
			String redirectViewPath = req.getContextPath()+logicalViewName.substring("redirect:".length());
			resp.sendRedirect(redirectViewPath);
		} else {
			req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
		}
	}
	
	private boolean validate(ProfessorVO professor, Map<String, String> errors) {
		boolean valid = true;
		
		if (StringUtils.isBlank(professor.getProNo())) {
			valid = false;
			errors.put("pro_no", "교수번호 누락");
		}
		if (StringUtils.isBlank(professor.getProName())) {
			valid = false;
			errors.put("pro_name", "교수이름 누락");
		}
		if (StringUtils.isBlank(professor.getProMajor())) {
			valid = false;
			errors.put("pro_major", "교과목 누락");
		}
		if (StringUtils.isBlank(professor.getProTelno())) {
			valid = false;
			errors.put("pro_telno", "전화번호 누락");
		}
		return valid;
	}
}
