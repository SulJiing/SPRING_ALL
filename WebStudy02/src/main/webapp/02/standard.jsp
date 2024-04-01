<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%> <%--trimDirectiveWhitespaces -> 페이지 소스 보기에서 공백제거 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/standard.jsp</title>
</head>
<body>
<h4>JSP(Java Server Page)</h4>
<h4>모델 : <%=request.getAttribute("sampleAttr")%></h4>
<pre>
	: 서블릿의 하위 스펙으로 동작하는 템플릿 엔진(View layer).
	
	소스 구성 요소(토큰의 종류)
	1. 정적 요소(Front-End) : HTML, CSS, JavaScript... 정적 텍스트 : 한번 만들면 바뀌지 않음, 소스에서 보임
	2. 동적 요소(Back-End)
		1) scriptlet : <% // java code %>
			<%
				String sample = "TEST"; // 지역변수이면서 블럭변수
				
// 				void test(){}
			%>
		2) directive(지시자) : <%--@ --%> 
			- include(optional) : 정적 include를 통해 2개 이상의 jsp소스를 하나로 합칠 때
			- page(required(반드시 사용해야 함)) : jsp페이지에 대한 구체적인 설정을 표현(속성으로 표현)
			- taglib(optional) : 커스텀 태그를 로딩할 때 사용 - 개발자가 필요해서 만든 html 태그
		3) expression(표현식) : <%=sample %>, <%=LocalDate.now() %>
		4) declaration(선언부) : <%! %> - 전역변수나 메소드 선언에 사용.
		<%! 
		private StringBuffer globalBuffer;
		void test(){}
		%>
		5) comment(주석) : <%-- JSP 주석 --%>, <!-- HTML 주석 -->
			<script type="text/javascript">
				// javascript 주석
			</script>
			<style type="text/css">
/* 				css 주석 */
			</style>
			- client side(Front-End) : 사용자에게 보임 - HTML주석, javascript 주석, css 주석
			- server side(Back-End) : 사용자에게 안보임 - Java주석, JSP주석
				-> server side를 써야하는 이유 - 보안목적, client side를 쓰면 응답데이터가 늘어나 문제가 생길 수 있음
		6) EL(Expression Language)
		7) JSTL(taglib과 관련)
		8) 기본객체(내장객체) - Servlet의 객체와 같음
		
		* Tomcat
		1. WAS(Web Application Server) : 로직의 실행으로 생성되는 동적 컨텐츠를 서비스하는 어플리케이션 서버.
		   WS(Web Server) : 이미지 파일, 동영상 파일, css, js, html... 등의 정적 자원 서버.
		2. Middle Tier(N-tier 구조에서 사용) : client와 raw 데이터 서버 사이의 어플리케이션이 운영되는 중간 티어.
		3. Servlet container : 서블릿의 셍명주기 관리자, 서블릿의 싱글톤 인스턴스르 생성하고, 해당 요청이 발생한 경우,
								그 요청을 처리할 수 있는 callback 메서드를 생성함.
		4. JSP container : 서블릿의 생명주기 관리자, *.jsp 템플릿 파일을 파싱하고, 서블릿 소스를 생성한 후, 해당 서블릿을 컴파일하고,
							해당 서블릿의 싱글턴 인스턴스를 생성하고, 요청 발생시 콜백 메서드를 실행함.
</pre>
</body>
</html>