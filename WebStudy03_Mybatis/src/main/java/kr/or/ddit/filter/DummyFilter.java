package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DummyFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 필터 초기화", this.getClass().getName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// request filtering
		HttpServletRequest req = (HttpServletRequest) request;
		log.info("{} 요청 필터링, filter1",req.getRequestURI());
		
		// 요청과 응답의 기준
		// 다음 filter로 제어권을 넘기는 역할(더 이상 filter가 없으면 Controller로 넘어감)
		chain.doFilter(request, response);
		
		// response filtering
		HttpServletResponse resp = (HttpServletResponse) response;
		log.info("응답 확인, filter1 : {}",resp.getContentType());
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
