<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>calculateForm.jsp</title>
<!-- 캐시 지우기 힘드니까 현재 시간 적어서 페이지가 같은 페이지라 인식하지 못하게 막음(쿼리스트링은 사용하지 않는 값이라 있어도 괜찮음) -->
<script src="<%=request.getContextPath()%>/resources/js/app/07/calculateForm_case3.js?"><%=System.currentTimeMillis()%></script>
</head>
<body>
<input type="radio" name="accept" value="text/html" data-fn-name="fnHtml"/>HTML
<input type="radio" name="accept" value="application/json" data-fn-name="fnJson"/>JSON

<form id="calForm" method="post" enctype="application/x-www-form-urlencoded" action="<%=request.getContextPath()%>/07/case3/calculator.do">
	<input type="number" name="left" placeholder="좌측피연산자"/>
	<select name="operator">
		<%=
		// enum 클래스에 담겨있는 상수들의 이름과 사인(캐릭터)를 가지고 와서 옵션에 넣음
			Stream.of(OperatorType.values()) // OperatorType의 모든 상수를 배열로 반환해 Stream.of로 스트림으로 변환
				.map((o)->String.format("<option value='%s' label='%c' />", o.name(), o.getSign()))
				.collect(Collectors.joining("\n")) // collect는 위 map에서 반환한 값들을 문자열로 묶음
		%>
	</select>
	<input type="number" name="right" placeholder="우측피연산자"/>
	<button type="submit">=</button>
</form>
<div id="resultArea"></div>
</body>
</html>