package kr.or.ddit.lecture.servlet;

import java.io.IOException;
import java.util.Enumeration;
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

import kr.or.ddit.common.paging.BootstrapFormBasePaginationRenderer;
import kr.or.ddit.common.paging.PaginationInfo;
import kr.or.ddit.common.paging.PaginationRenderer;
import kr.or.ddit.lecture.service.LectureService;
import kr.or.ddit.lecture.service.LectureServiceImpl;
import kr.or.ddit.vo.LectureVO;
import kr.or.ddit.vo.ProfessorVO;

@WebServlet("/lecture/selectLecture.do")
public class SelectLectureControllerServlet extends HttpServlet{
   LectureService service = new LectureServiceImpl();
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ProfessorVO proInfo = (ProfessorVO) session.getAttribute("proInfo");
		String proNo=proInfo.getProNo();
		String subCd=proInfo.getSubCd();
		List<LectureVO> lecture =service.retrieveLecture(proNo,subCd);
		session.setAttribute("lecture", lecture);
		
		String logicalViewName = "/lecture/selectLecture";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
      
   }
   }