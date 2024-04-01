<%@page import="java.time.format.TextStyle"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.time.Month"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="java.time.YearMonth"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/calendar.css">
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/app/06/case3/calendarForm.js"></script>
</head>
<body>
<%
   Locale locale = request.getLocale();
   YearMonth thisMonth = YearMonth.now();
   
//    Locale locale = (Locale)request.getAttribute("locale");
//    YearMonth thisMonth = (YearMonth)request.getAttribute("thisMonth");
%>
<form id="calForm" method='post'>
   <input type="number" name="year" placeholder="연도" data-init-value="<%=thisMonth.getYear() %>">
   <select name="month" data-init-value="<%=thisMonth.getMonthValue()%>">
      <option value>월</option>
      <%=
//       request.getAttribute("st")
         Stream.of(Month.values())
         .map(m->String.format("<option value='%d' >%s</option>",m.getValue(),m.getDisplayName(TextStyle.FULL, locale) ))
         .collect(Collectors.joining("\n"))
      %>
   </select>
   <select name="locale" data-init-value="<%=locale.toLanguageTag()%>">
      <option value>locale</option>
      <%=
//       request.getAttribute("arr")
         Arrays.stream(Locale.getAvailableLocales())
               .map( l -> String.format("<option value='%s'>%s</option>",l.toLanguageTag(), l.getDisplayName(l)))
               .collect(Collectors.joining("\n"))
      %>
   </select>
</form>
<div id='calArea'>

</div>

<!-- 달력이 처리되는 과정 -->
<!-- 	: calForm이 전송(submit이벤트)되면서 파라미터가 서버로 전달됨 -->
<!-- 	  form의 submit이벤트는 default action : 동기요청 -->


</body>
</html>