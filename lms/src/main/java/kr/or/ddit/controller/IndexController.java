package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/*
//@Controller
@WebServlet("/")
public class IndexController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String logicalViewName = "/";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
	}
}*/
@Controller
@RequestMapping("/index.do")
public class IndexController {
	
	@RequestMapping
	public String index() {
		return "index";
	}
}

