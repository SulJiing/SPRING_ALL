<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/responseDesc.jsp</title>
</head>
<body>
<h4>서버에서 클라이언트로 전송되는 응답 형태 : HttpServletResponse</h4>
<pre>
	1. Response Line : status code
		 status code : 요청 처리의 성공/실패 여부를 표현할 수 있는 세자리 숫자.
		 
		 1) 100~ : Http 프로토콜(Connect-Less, State-Less)
		 			ING... (아직 진행 중), webSocket 프로토콜에서 사용되고, 주로 양방향 실시간 통신이나 push메세지 서비스에 활용됨.
		 2) 200~ : OK(<%=HttpServletResponse.SC_OK%>)
		 3) 300~ : response body가 없기 때문에 클라이언트가 응답을 받은 후 추가액션을 행하게 됨
		 	 304 : Not Modified(<%=HttpServletResponse.SC_NOT_MODIFIED%>) : 캐시 영역 검색
			 302/037 : Moved : 자원의 새로운 위치를 대상으로 새로운 요청을 전송함.
		 
		 4) 400~ : client side error
		 	 400 : <%=HttpServletResponse.SC_BAD_REQUEST %> 
		 	 		: 필수 파라미터 누락, 요청 데이터 타입 문제, 요청 데이터 길이가 너무 길때(요청 검증시 의도적으로 전송)
		 	 401/403 : <%=HttpServletResponse.SC_UNAUTHORIZED%> / <%=HttpServletResponse.SC_FORBIDDEN%> 
		 	 		: 보안 처리 - 접근을 제한할 때
		 	 404 : <%=HttpServletResponse.SC_NOT_FOUND%> 
		 	 		: url이 잘못되었을 때
		 	 405 : <%=HttpServletResponse.SC_METHOD_NOT_ALLOWED%> 
		 	 		: 서블릿을 통해 오버라이딩한 요청이 doGet하나인데 doPost를 요청할 때 - 해당 메서드의 콜백 메서드가 정의되지 않았을 때
		 	 406 : <%=HttpServletResponse.SC_NOT_ACCEPTABLE%>
		 	 		: xml - 마샬링을 할 수 없을 때 (reqquest Accept header / response Content-Type)헤더를 처리할 수 없음
		 	 415 : <%=HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE%>
		 	 		: request Content-Type 헤더를 처리할 수 없음
		 5) 500~ : server side error
		 			: 서버의 정보를 노출하는 것을 막고자 자세한 상태코드를 보여주지 않음
		 
	2. Response Header(name/value)
		Content-*
		Content-Length : response body의 길이
		Content-Type   : response body content's MIME
		
		1) Content-Type	: response content MIME
		2) Cache-Control: 캐시 제어
		3) Refresh		: auto-request
		4) Location		: 자원의 위치가 새로운 곳으로 이동한 경우, 새로운 위치정보를 제공하는 헤더(sendRedirect)
		
	3. Response Body(Content Body, Message Body) : 응답 컨텐츠 영역
</pre>
</body>
</html>