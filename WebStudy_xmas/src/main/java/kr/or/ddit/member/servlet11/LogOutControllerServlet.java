package kr.or.ddit.member.servlet11;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login/logOut.do")
public class LogOutControllerServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션을 unbind로 사라지게 함
		req.getSession().invalidate();
		// 웰컴페이지로 보내기(인증처리구조는 redirect)
		resp.sendRedirect(req.getContextPath()+"/");
	}
}
