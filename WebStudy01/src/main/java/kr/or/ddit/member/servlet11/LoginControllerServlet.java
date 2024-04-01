package kr.or.ddit.member.servlet11;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginProcess.do")
public class LoginControllerServlet extends HttpServlet {
//	AuthenticateService와 의존관계 형성
	private AuthenticateService service = new AuthenticateServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//		1. 파라미터 2개 확보(누락되면 정상적인 요청이 아니므로 검증이 필요) - 400
//		2. 서비스를 통해서 인증여부 판단 - 성공시 콘솔에 확인, 실패시 콘솔에 실패
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(memPass);

		boolean valid = validate(inputData);
		String logicalViewName = null;
		String message = null;
		// session은 한 클라이언트만 소유하고 독점적
		HttpSession session = req.getSession();

		if (valid) {
			if (service.authenticate(inputData)) {
				if(session.isNew()) {
					message = "브라우저의 설정 오류, 쿠키 정보를 확인하세요.";
					logicalViewName = "/login/loginForm.jsp";
				} else {
					// 클라이언트의 새로운 요청 - 로그인 성공
					logicalViewName = "/";
					session.setAttribute("authId", inputData.getMemId());
					int maxAge = Optional.ofNullable(req.getParameter("rememberMe"))
										.map(rv->60*60*24*3)
										.orElse(0);
					Cookie rememberMeCookie = new Cookie("rememberMe", inputData.getMemId());
					rememberMeCookie.setMaxAge(maxAge);
					rememberMeCookie.setPath(req.getContextPath());
					resp.addCookie(rememberMeCookie);
					System.out.println(rememberMeCookie);
				}
				// 사용 못함 - req에서 끝나기 때문에 사라짐
//				req.setAttribute("authId", inputData.getMemId());
			} else {
				// 클라이언트의 잘못된 요청 - 로그인 실패
				logicalViewName = "/login/loginForm.jsp";
				message = "아이디나 비밀번호 오류";
			}
		} else {
			// ID, PASSWORD 둘다입력하지 않음
//			resp.sendError(400);
			logicalViewName = "/login/loginForm.jsp";
			message = "아이디와 비밀번호를 모두 입력하세요.";
		}
		session.setAttribute("message", message);
		resp.sendRedirect(req.getContextPath() + logicalViewName);
	}

	private boolean validate(MemberVO inputData) {
		boolean valid = true;
		
		if(StringUtils.isBlank(inputData.getMemId())) {
			valid = false;
		}
		if(StringUtils.isBlank(inputData.getMemPass())) {
			valid = false;
		}
		return valid;
	}
}
