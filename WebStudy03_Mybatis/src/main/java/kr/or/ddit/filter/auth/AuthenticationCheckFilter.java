package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 * /kr/or/ddit/auth/SecuredResources.properties
 * 보호 자원에 대한 요청을 확인하고, 해당 요청의 인증 여부를 결정하기 위한 필터
 * 인증 여 : 인증된 요청을 해당 자원으로 제어권을 이동시킴
 * 인증 부 : 인증이 안된 요청인 경우, loginForm으로 제어권을 이동시킴
 */
@Slf4j
public class AuthenticationCheckFilter implements Filter{
	// key : 보호자원의 (URL), value : 보호등급
	private Map<String, String[]> securedResources;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 등록된 보호자원을 읽어옴
		String securedResourcesPath = "/kr/or/ddit/auth/SecuredResources.properties";
		Properties resourceProps = new Properties();
		securedResources = new LinkedHashMap<>();
		try(
			InputStream is = this.getClass().getResourceAsStream(securedResourcesPath);
		){
			resourceProps.load(is);
			resourceProps.forEach((k,v)->{
				// key
				String url = k.toString().trim();
				// value
				String[] tmpArray = v.toString().trim().split("\\s*,\\s*");
				Arrays.sort(tmpArray);
				securedResources.put(url,tmpArray);
				log.info("보호자원 로딩 - {} : {}",url,tmpArray);
			});
			// application scope - 다음 필터(AuthorizationCheckFilter)로 읽은 Properties 파일을 보내주기 위함
			filterConfig.getServletContext().setAttribute("securedResources", securedResources);
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		//				주소창의 URI 					주소창에는 ContextPath가 없기 떄문
		String uri = req.getRequestURI().substring(req.getContextPath().length());
		
		boolean pass = false;
		boolean secured = securedResources.containsKey(uri);
		
		// 밑에서 pass의 여부를 결정
		if(secured) {
//			1. 보호 자원의 요청 여부
//		 	여 : 
//		 		2. 인증 여부
//			boolean authenticated = req.getSession().getAttribute("authMember") != null;
			boolean authenticated = req.getUserPrincipal() != null;
			if(authenticated) {
//		 		여 : 통과
				pass = true;
			} else {
//		 		부 : 접근 통제
				pass = false;
			}
			
		} else {
//		 	부(비보호자원에 대한 요청) : 통과
			pass = true;
		}
		 			
		if(pass) {
			// 통과 시켜도 되는 요청이 경우
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + "/login/loginForm.jsp");
		}
	}

	@Override
	public void destroy() {
		
	}
}