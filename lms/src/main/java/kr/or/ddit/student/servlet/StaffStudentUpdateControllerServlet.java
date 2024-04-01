package kr.or.ddit.student.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.student.service.StudentOthersService;
import kr.or.ddit.student.service.StudentOthersServiceImpl;
import kr.or.ddit.student.service.StudentService;
import kr.or.ddit.student.service.StudentServiceImpl;
import kr.or.ddit.vo.StudentVO;

@WebServlet("/staff/staffStudentUpdate.do")
public class StaffStudentUpdateControllerServlet extends HttpServlet{
	
	private StudentOthersService othersService = new StudentOthersServiceImpl();
	private StudentService service = new StudentServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		session.removeAttribute("student");
		
		String logicalViewName = "staff/student/staffStudentUpdate";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String stdNo = req.getParameter("stdNo");
		String stdName = req.getParameter("stdName");
		String stdTelno = req.getParameter("stdTelno");
		String stdId = req.getParameter("stdId");
		String stdAddress = req.getParameter("stdAddress");
		String proNo = req.getParameter("proNo");
		
		StudentVO student = new StudentVO();
		student.setStdNo(stdNo);
		student.setStdName(stdName);
		student.setStdTelno(stdTelno);
		student.setStdAddress(stdAddress);
		student.setStdId(stdId);
		student.setProNo(proNo);
		
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(student,errors);
		
		String logicalViewName = null;
		if(valid) {
			ServiceResult result = service.modifyStudent(student);	
			String message = null;
			
			switch(result) {
			case FAIL :
				logicalViewName = "/staff/student/taffStudentUpdate";
				message = "서버에 잠시 오류가 있었습니다.";
				break;
			case OK :
				logicalViewName = "redirect:/staff/staffStudentList.do";
				break;
			}
			
		}else {
			logicalViewName = "/staff/student/staffStudentUpdate";
		}
		
		if(logicalViewName.startsWith("redirect:")) {
			String redirectViewPath = req.getContextPath() + logicalViewName.substring("redirect:".length());
			resp.sendRedirect(redirectViewPath);
		}else {
			req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
		}
		
	}

	private boolean validate(StudentVO student, Map<String, String> errors) {
		boolean valid = true;
		
		if(StringUtils.isBlank(student.getStdNo())) {
			valid = false;
			errors.put("stdNo", "아이디 누락");
		}
		if(StringUtils.isBlank(student.getStdName())) {
			valid = false;
			errors.put("stdName", "이름 누락");
		}
		if(StringUtils.isBlank(student.getStdTelno())) {
			valid = false;
			errors.put("stdTelno", "전화번호 누락");
		}
		if(StringUtils.isBlank(student.getStdId())) {
			valid = false;
			errors.put("stdId", "주민번호 누락");
		}
		if(StringUtils.isBlank(student.getStdAddress())) {
			valid = false;
			errors.put("stdAddress", "주소 누락");
		}
		if(StringUtils.isBlank(student.getProNo())) {
			valid = false;
			errors.put("proNo", "지도교수 누락");
		}
		
		return valid;
	}
	
	
}
