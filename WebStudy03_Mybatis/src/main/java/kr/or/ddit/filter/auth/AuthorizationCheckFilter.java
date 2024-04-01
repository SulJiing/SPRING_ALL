package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.principal.MemberVOWrapper;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 보호자원에 대한 요청에 대한 요청을 확인하고, 인가 여부를 결정하는 필터
 * 인가 여 : 해당 자원에 대한 접근 권한을 부여받은 사용자이므로, 해당 자원으로 제어권 이동 
 * 인가 부 : 해당 자원에 대한 접근 권한이 없는 사용자이므로, 403 상태코드로 응답 전송
 */
@Slf4j
public class AuthorizationCheckFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 보내준 데이터 꺼내기
		Map<String, String[]> securedResources = 
				(Map) request.getServletContext().getAttribute("securedResources");
		
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI().substring(req.getContextPath().length());
		
		boolean pass = false;
		boolean secured = securedResources.containsKey(uri);
		if(secured) {
			MemberVOWrapper wrapper = (MemberVOWrapper) req.getUserPrincipal();
			MemberVO authMember = wrapper.getRealUser();
			String userRole = authMember.getMemRole();
			// Value값을 가져옴
			String[] authorities = securedResources.get(uri);
			// 이진 탐색 구조
			int idx = Arrays.binarySearch(authorities, userRole);
			log.info("{} // {}",authorities,userRole);
			System.out.println("=============="+idx);
			if(idx<0) {
				pass = false;
			} else {
				pass = true;
			}
			System.out.println(pass);
		} else {
			pass = true;
		}
		
//		인가
		if(pass) {
			chain.doFilter(request, response);
		} else {
			HttpServletResponse resp = (HttpServletResponse) response;
			// 접근 제어
			resp.sendError(HttpServletResponse.SC_FORBIDDEN); 
		}
	}

	@Override
	public void destroy() {
	}
}