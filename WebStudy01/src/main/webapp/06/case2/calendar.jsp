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
// 	Locale locale = request.getLocale(); // 우선으로 설정된 지역을 사용하는 것
// 	Locale locale = Locale.CANADA_FRENCH; // 캐나다로 바꾼 것
	Locale locale = Optional.ofNullable(request.getParameter("locale")) // 지역으로 언어바꾸기
							.filter(lp->!lp.trim().isEmpty())
							.map(lp->Locale.forLanguageTag(lp))
							.orElse(request.getLocale()); // 없으면 클라인언트 기준 언어로
	
// 	Locale locale = Locale.getDefault(); // 서버의 지역을 사용하는 것
	ZoneId zone = ZoneId.systemDefault(); // 시스템의 기본 시간대
	
// 	LocalDateTime current = LocalDateTime.now(zone);
	ZonedDateTime current = ZonedDateTime.now(zone); // 지정된 시간대 가져오기
	FormatStyle fullStyle = FormatStyle.FULL; // 형식 지정
	
// 	YearMonth thisMonth = YearMonth.from(current); // 현재 월 가져오기 근데 밑에서 쓰기 때문에 필요가 없어짐
	
	int targetYear = Optional.ofNullable(request.getParameter("year")) // 파라미터의 기본 시간대 잡기
							.map(yp->new Integer(yp))
							.orElse(YearMonth.from(current).getYear());
	YearMonth thisMonth = Optional.ofNullable(request.getParameter("month")) // 여기 thisMonth는 이제 무조건 현재가 아닐수도 있음
							.map(mp->YearMonth.of(targetYear, Integer.parseInt(mp)))
							.orElse(YearMonth.from(current));
			
	YearMonth beforeMonth = thisMonth.minusMonths(1); // 한 달 빼기
	YearMonth nextMonth = thisMonth.plusMonths(1); // 한 달 더하기
	
	WeekFields weekFields = WeekFields.of(locale); // 주의 첫번째 요일을 사용해 weekFields 생성
	DayOfWeek firstDOW = weekFields.getFirstDayOfWeek(); // 주의 첫번째 요일 가져오기
	LocalDate firstDate = thisMonth.atDay(1); // 이번달의 첫번째 날짜 가져오기
	int firstDateDOW = firstDate.get(weekFields.dayOfWeek()); // 이번달의 첫번째 날짜의 요일 가져오기
	int offset = firstDateDOW -1; // 앞에 들어가는 빈칸 = 전달의 날짜
	
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