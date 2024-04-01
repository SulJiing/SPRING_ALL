<%@page import="java.text.MessageFormat"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	border : 1px solid;
}
tr,td,th {
	border : 1px solid;
}
</style>
</head>
<body>
<h4>Http Request(요청) => HttpServletRequest </h4>
<a href="?param1=asd&param2=23">A태그를 통한 GET 요청</a> <!-- a태그는 무조건 GET 요청 -->

<form method="post">
	<input type="text" name="param1">
	<input type="text" name="param2">
	<button type="submit">POST 전송</button>
</form>
<pre>
	web : 역할이 다른 client / server가 content로 표현되는 메세지를 교환하는 공간.
	client -> server : request packging (편지지+편지봉투)
	
	1. Request Line (수신자) : URL(URI)-명사의 역할(자원에 대한 식별자), request Method-동사의 역할(행위정보, CRUD...)
		<%=request.getRequestURI() %>, <%=request.getMethod()%>
		request Method
		1) GET(default method, Read) : form을 형성하지 않은 일반 요청의 메서드.
		2) POST(Create) : 클라이언트가 서버로 새로운 자원을 등록하기 위해 전송.
		3) PUT(Update) : 서버의 자원의 모든 속성을 수정하기 위한 수정.
		4) PATCH(Update) : 서버의 자원의 선택적 속성을 수정하기 위한 수정.
		5) DELETE		: 서버의 자원 삭제.
		6) OPTION : pre-flight 요청에 사용되는 메서드.
		7) HEAD : 차후 응답 전송시 body가 없는 line+header의 응답이 전송됨.
		8) TRACE : 서버를 디버깅할 목적의 요청 메서드.
	2. Request Header (송신자) : 클아이언트와 현재 요청에 대한 부가정보(meta data);
		String value = getHeader(String name)
		Enumeration&lt;String&gt; getHeaderNames
		
		Accept-*	: ex) Accept(response content tpye), Accept-Encoding(response archive format), 
						  Accept-Language(response language + country format - Loacle)
		Content-*	: post 메서드로 body의 content에 대한 메타 데이터.(즉, 전제조건이 POST 요청이 경우)
			ex) Content-Type (request content type : MIME), Content-Length (request content length)
		User-Agent	: 사용자 OS, 랜더링 엔진, 브라우저의 종류...
		
	<%=request.getHeader("Accept") %>
	
	3. Request Body (편지지) : Content Body, Message Body, only POST 요청에서만 형성
		contnet length : <%=request.getContentLength() %>
		contnet type : <%=request.getContentType() %>
	
	client가 서버에게 보내는 의도적인 컨텐츠
	1. request parameter : GET, POST 둘다 가능
	
		1) post request : body를 통해 전송 - req.getParameter*..
		
		2) get reques	: line을 통해 url의 query string형태로 전송 - req.getQueryString()
		
	2. multipart request : body를 통해 전송, body를 여러개의 part로 분리하여 전송 (body는 post요청이 있을 때만 전송가능) - req.getPart, req.getParts
	3. request payload : body를 통해 전송, JSON / XML - req.getInputStream()
</pre>
<table>
	<thead>
		<tr>
			<th>헤더이름</th>
			<th>헤더값</th>
		</tr>
	</thead>
	<tbody>
		<%
			Enumeration<String> headerNames = request.getHeaderNames();
			while(headerNames.hasMoreElements()) {
				String name = (String) headerNames.nextElement();
				String value = request.getHeader(name);
				out.println(
					String.format("<tr><th>%s</th><td>%s</td></tr>", name, value)
				);
				
			}
		%>
	</tbody>
</table>
</body>
</html>