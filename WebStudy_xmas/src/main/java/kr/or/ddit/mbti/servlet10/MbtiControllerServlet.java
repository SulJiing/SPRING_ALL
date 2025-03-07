package kr.or.ddit.mbti.servlet10;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mbti.exception.MbtiNotFoundException;
import kr.or.ddit.mbti.service.MbtiService;
import kr.or.ddit.mbti.service.MbtiServiceImpl;
import kr.or.ddit.vo.MbtiVO;

@WebServlet("/10/mbti")
public class MbtiControllerServlet extends HttpServlet{
//	private MbtiDAO dao = new InMemoryMbtiDAOImpl();
	
	private MbtiService service = new MbtiServiceImpl();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	private void listToJson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Set<Entry<String, String>> entrySet = service.retrieveEntrySet();
		req.setAttribute("entrySet", entrySet);

		Map<String, String> entryMap = service.retrieveEntryMap();
		req.setAttribute("entryMap", entryMap);

		String view = "/jsonView.do";
		req.getRequestDispatcher(view).forward(req, resp);
	}
	
	private void singleToHTML(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		
		if (type == null || type.isEmpty()) {
			resp.sendError(400, "필수파라미터누락");
			return;
		}
		try {
			MbtiVO model = service.retrieveMbti(type);
			// 쿠키 생성
			System.out.println(type);
			Cookie mbtiCookie = new Cookie("mbtiCookie", type);
			mbtiCookie.setMaxAge(60*60*24*5);
			mbtiCookie.setPath(req.getContextPath());
			resp.addCookie(mbtiCookie);
			// 브라우저내에 캐시를 남기지 않게함
			resp.setHeader("Cache-Control","no-cache");
			resp.addHeader("Cache-Control","no-store");
			System.out.println(mbtiCookie);
			
			String view = model.getLogicalPath();
			req.getRequestDispatcher(view).forward(req, resp);
			
		}catch (MbtiNotFoundException e) {
			resp.sendError(404, e.getMessage());
			return;
		}
	}
	
	// type 파라미터의 존재여부에 따라 전체 MBTI 조회(json)와 한개의 유형조회(html)로 구분됨.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("accept");
		
		if(accept.contains("json")) {
			listToJson(req, resp);
		}else {
			singleToHTML(req, resp);
		}
	}
}