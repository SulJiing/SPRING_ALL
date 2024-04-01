<%@page import="kr.or.ddit.vo.CalendarVO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.time.Month"%>
<%@page import="java.util.Optional"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.DayOfWeek"%>
<%@page import="java.time.temporal.WeekFields"%>
<%@page import="java.time.YearMonth"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.ZonedDateTime"%>
<%@page import="java.time.format.FormatStyle"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	CalendarVO calVO = (CalendarVO)request.getAttribute("calendar");
	YearMonth beforeMonth = calVO.getBeforeMonth();
	ZonedDateTime current = calVO.getCurrent();
	LocalDate firstDate = calVO.getFirstDate();
	DayOfWeek firstDOW = calVO.getFirstDOW();
	FormatStyle fullStyle = calVO.getFormatStyle();
	
	Locale locale = calVO.getLocale();
	YearMonth nextMonth = calVO.getNextMonth();
	int offset = calVO.getOffset();
	YearMonth thisMonth = calVO.getThisMonth();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	th,td {
		border: 1px solid black;
	}
	table {
 		border-collapse: collapse; <!-- 경계선 없애기 --> 
	}
	.sunday {
		color: red;
	}
	.saturday {
		color: blue;
	}
	.before,.after {
		color: silver;
	}
</style>
</head>
<body>
<!-- 현재 날짜와 시간을 특정 스타일과 지역 설정으로 포맷하여 출력 -->
<h4><%=current.format(DateTimeFormatter.ofLocalizedDateTime(fullStyle,fullStyle).withLocale(locale)) %></h4>
<h4>
<a class="control-a" href="javascript:;" data-year="<%=beforeMonth.getYear() %>" data-month="<%=beforeMonth.getMonthValue() %>">이전 달</a>
<!-- 현재 월과 년도를 사용자 정의 패턴과 지역 설정으로 포맷하여 출력 -->
<%=thisMonth.format(DateTimeFormatter.ofPattern("yyyy,MMMM",locale)) %>
<a class="control-a" href="javascript:;" data-year="<%=nextMonth.getYear() %>" data-month="<%=nextMonth.getMonthValue() %>">다음 달</a>

</h4>

<table class="clz1 clz2">
<%
out.println("<thead>");
for(int col=0; col<7; col++){
	DayOfWeek tmp = firstDOW.plus(col); // 현재 반복하는 요일을 가져오기
	
	out.println( // 현재 요일의 전체 이름을 가져와서 테이블 헤더에 출력
		String.format("<th>%s</th>", tmp.getDisplayName(TextStyle.FULL, locale))		
	);
}
out.println("</thead>");
out.println("<tbody>");
LocalDate tmpDate = firstDate.minusDays(offset); // offset전 날짜 즉, 첫날
for(int row=1; row<=6; row++){
	out.println("<tr>");
	for(int col=1; col<=7; col++){
		YearMonth tmpMonth = YearMonth.from(tmpDate);
		
		// 이전 달, 이후 달, 현재 달 여부에 따라 클래스를 지정
		String clz = tmpMonth.isBefore(thisMonth) ? "before" : 
			tmpMonth.isAfter(thisMonth) ? "after" : "this-month";
		
		clz += " "+tmpDate.getDayOfWeek().name().toLowerCase();
		
		// 날짜를 출력하고 해당 월에 따라 클래스를 적용
		out.println(
			String.format("<td class='%2$s'>%1$d</td>", tmpDate.getDayOfMonth(), clz)		
		);
		tmpDate = tmpDate.plusDays(1); // 상수이기 때문에 1씩 증가하면서 새로운 객체가 생성됨
	}
	out.println("</tr>");
}
out.println("</tbody>");
%>
</table>
<script type="text/javascript">
	calForm.year.value = "<%=thisMonth.getYear()%>";
	calForm.month.value = "<%=thisMonth.getMonthValue()%>";
	calForm.locale.value = "<%=locale.toLanguageTag()%>";
	calForm.addEventListener("change", (event)=>{
		console.log(event);
		event.target.form.requestSubmit();
	});
	document.querySelectorAll(".control-a").forEach((el, idx)=>{
		el.addEventListener("click",(event)=>{
			calForm.year.value = el.dataset.year;
			calForm.month.value = el.dataset.month;
			calForm.requestSubmit();
		});
	});
</script>
</body>
</html>