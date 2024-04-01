<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/cacheControl.jsp</title>
</head>
<body>
<h4>캐시 제어</h4>
<h4>캐싱하지 않는 방법</h4>	
<pre>
	Cache : 자원이 네트워크를 통해 전송되는 동안 발생하는 부하와 latency time을 줄이기 위한 저장 데이터 형태.
	
	: Pragma(http 1.0ver), Cache-control(http 1.1ver), Expires(version에 무관, Date(long)).. 응답 헤더로 캐시 제어 - 캐시를 제어하는 3가지
	response.setHeader(name, value), setIntHeader(name, int value), setDateHeader(name, long value)
	response.addHeader(name, value), addIntHeader(name, int value), addDateHeader(name, long value)
	<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-control", "no-cache"); // 저장했더라도 나한테 물어보고 다시 써
		response.addHeader("Cache-control", "no-store"); // 아예 저장x // set은 덮어씌우기 때문에 add
		response.setDateHeader("Expires", 0); // 남기지 말아라
	%>
</pre>
</body>
</html>