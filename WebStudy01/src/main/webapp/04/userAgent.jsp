<%@page import="kr.or.ddit.servlet05.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
클라이언트의 브라우저 종류를 확인하고,
다음과 같은 메세지를 출력할 것.<br>
<!-- <h4>당신의 브라우저는 "크롭|엣지|웨일"입니다.</h4> -->
<%=request.getHeader("user-agent") %>
<%
String agent = request.getHeader("user-agent");
agent = agent.toUpperCase();

String messagePattern = "<h4>당신의 브라우저는 %s 입니다.</h4>";
String browserName = null;
// case1
// if (agent.contains("EDG")) {
// 	browserName = "엣지";
// } else if (agent.contains("WHALE")) {
// 	browserName = "웨일";
// } else if (agent.contains("CHROME")) {
// 	browserName = "크롬";
// } else if (agent.contains("SAFARI")) {
// 	browserName = "사파리";
// } else {
// 	browserName = "기타";
// }

// case2
// Map<String,String> browserType = new LinkedHashMap<>();
// browserType.put("EDG","엣지");
// browserType.put("WHALE","웨일");
// browserType.put("CHROME","크롬");
// browserType.put("SAFARI","사파리");
// browserType.put("OTHER","기타");

// browserName = browserType.get("OTHER");
// for(Entry<String,String> entry : browserType.entrySet()) {
// 	if(agent.contains(entry.getKey())) {
// 		browserName = entry.getValue();
// 		break;
// 	}
// }

// case3
// browserName = BrowserType.OTHER.getBrowserName();
// for(BrowserType tmp : BrowserType.values()) {
// 	if(agent.contains(tmp.name())) {
// 		browserName = tmp.getBrowserName();
// 		break;
// 	}
// }

// case4
browserName = BrowserType.findBrowserName(request.getHeader("user-agent"));

%>
<%=String.format(messagePattern, browserName) %>
<!-- case4 -->
<h4><%=BrowserType.findBrowserType(agent).getBrowserName() %></h4>
</body>
</html>