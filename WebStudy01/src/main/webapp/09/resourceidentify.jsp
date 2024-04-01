<%@page import="kr.or.ddit.servlet08.ServerTimeServlet"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/09/resourceidentify.jsp</title>
</head>
<body>
<h4>자원의 종류와 종류에 따른 식별 방법</h4>
<pre>
	자원의 위치와 접근방법에 따라 3가지 분류
	1. file system resource : 파일시스템상의 절대경로(물리경로)를 사용해서 접근가능.
		ex) D:\01.medias\image\ala.jpg
		<%
			File fsRes = new File("D:\\01.medias\\image\\ala.jpg");
			out.println(fsRes.length());
		%>
	2. web resource			: URL이라는 논리주소의 형태로 접근가능.
		ex) https://www.google.co.kr/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png - 구글 로고
		ex) http://localhost/WebStudy01/resources/images/ala.jpg - 내 코알라 (브라우저)
		/WebStudy01/src/main/webapp - /resources/images/ala.jpg - 내 코알라 (qualified 경로)
		<%
			String logicalPath = "/resources/images/ala.jpg";
			String realPath = application.getRealPath(logicalPath);
			out.println("real path : "+realPath);
			File webRes= new File(realPath);
			out.println(webRes.length());
		%>
	3. classpath resource	: classpath 이후의 경로부터 시작되는 qualified name의 형태로 접근가능.
		ex) kr/or/ddit/images/ala.jpg
		<%
			//A a = new A();
// 			logicalPath = "kr/or/ddit/images/ala.jpg";
// 			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
// 			ClassLoader classLoader = ServerTimeServlet.class.getClassLoader();
// 			URL url = classLoader.getResource(logicalPath);
			logicalPath = "/kr/or/ddit/images/ala.jpg";
			URL url = ServerTimeServlet.class.getResource(logicalPath);
			out.println(url);
			File cdRes = new File(url.toURI());
			out.println(cdRes.length());
		%>
</pre>
<h4>URI vs URL</h4>

URI (Uniform Resource Identify) : 자원의 식별방법 - 더 큰 개념
URL (Uniform Resource Locator) 	: 자원을 식별할 때 위치를 기준으로 식별하는 방식.
URN (Uniform Resource Naming)	: 자원을 식별할 때 명명된 이름으로 식별하는 방식.
URC (Uniform Resource Content)	: 자원을 식별할 떄 해당 자원이 가진 컨텐츠의 특성으로 식별하는 방식
<br>
URL - 절대경로 (window.location의 속성들은 생략가능)
protocol://ip[domain]:port/context/depth..../resourceName - (생략가능한 것:protocol,host)

client side : context path를 포함한 경로 형태.
server side : context path 이후의 경로만 사용함.

<img src="http://localhost/WebStudy01/resources/images/ala.jpg"/>
<img src="//localhost/WebStudy01/resources/images/ala.jpg"/>
<img src="/WebStudy01/resources/images/ala.jpg"/>
<img src="<%=request.getContextPath()%>/resources/images/ala.jpg"/> <!-- /WebStudy01는 생략못함 -->

상대경로 : 현재 문서의 출처를 기준으로 자원의 위치가 상대적으로 표현됨.
<img src="../resources/images/ala.jpg">
</body>
</html>