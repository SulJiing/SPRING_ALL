package kr.or.ddit.member.servlet11;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidateUtils;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/member/memberUpdate.do")
public class MemberUpdateControllerServlet extends HttpServlet {
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 현재 로그인한 사용자의 기본정보를 초기값으로 갖고 있는 수정 양식의 제공
		HttpSession session = req.getSession();
		
//		MemberVO authMember = (MemberVO)session.getAttribute("authMember");
//		MemberVO member2 = service.retrieveMember(authMember.getMemId());
//		req.setAttribute("member", member2);
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		System.out.println("asdasd"+member.getMemName());
		req.setAttribute("member", member);
		
		Map<String,String[]> parameterMap = req.getParameterMap();
		PopulateUtils.populate(member, parameterMap);
		
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		String logicalViewName = "member/memberEdit";
		req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Post 파라미터로 수정 절차 진행
//		1. 특수문자 디코딩 설정
		
		HttpSession session = req.getSession();
//		2. 17개의 파라미터를 받고 -> Command Object로 캡슐화(MemberVO)
		MemberVO member = new MemberVO();
		// 아이디 중복 or 실패했을 때 데이터를 남겨주기 위함
		req.setAttribute("member", member);
		Map<String,String[]> parameterMap = req.getParameterMap();
		
		PopulateUtils.populate(member, parameterMap);
		
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		member.setMemId(authMember.getMemId());
		log.info("===>{}",member);
		
// 		2-1. 검증작업 : 통과, 불통
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(member,errors,UpdateGroup.class);
		
		String logicalViewName = null;
		if(valid/*errors.isEmpty()*/) {
//			3. 수정 로직 처리
			ServiceResult result = service.modifyMember(member);
//			4. 로직의 실행 결과에 따른 뷰 선택
			String message = null;
			switch (result) {
			case OK:
				session.removeAttribute("member");
				logicalViewName = "redirect:/"; // Post-Redirect-Get
				break;
			case FAIL:
				logicalViewName = "member/memberEdit";
				message = "서버 오류";
				break;
			default:
				logicalViewName = "member/memberEdit";
				message = "인증 실패";
				break;
			}
			req.setAttribute("message", message);
			
		} else {
			logicalViewName = "member/memberEdit";
		}
		
//		5. 해당 뷰로 이동
		if(logicalViewName.startsWith("redirect:")) {
			String redirectViewPath = req.getContextPath()+logicalViewName.substring("redirect:".length());
			resp.sendRedirect(redirectViewPath);
		} else {
			req.getRequestDispatcher("/"+logicalViewName+".miles").forward(req, resp);
		}
	}
}