<%@page import="java.time.LocalDate"%>
<%@page import="java.time.Month"%>
<%@page import="java.time.DayOfWeek"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style type="text/css">
table {
	border-collapse: collapse;
	width : 900px;
	height: 400px;
}
tr,td,th {
	border: 1px solid;
	text-align: center;
}
h4 {
	text-align : center;
}
</style>
</head>
<body>
** JSP스펙을 이용한 Model1 구조의 달려 구현.<br>
1. 선택 년도와 월이 없으면, 현재 달력을 랜더링.<br>
2. 이전달과 다음달의 달력 처리<br>
3. 임의의 년도와 월의 달력 처리<br>
4. 제공한 언어의 목록 중 선택한 Locale을 이용한 언어별 달력 처리<br>
5. 제공한 TimeZone목록 중 선택한 TimeZone을 이용하여 오늘 날짜에 대한 식별 처리<br>

<%

LocalDate now = LocalDate.now();

int lastDayOfMonth = now.lengthOfMonth();

int year = now.getYear();

int month = now.getMonthValue(); // 숫자 월

DayOfWeek day = now.getDayOfWeek(); // 요일

int Week = now.getDayOfWeek().getValue(); // 요일(월1)

int dayOfWeekNumber = day.getValue();

request.setAttribute("now", now);
request.setAttribute("year", year);
request.setAttribute("month", month);
request.setAttribute("Week", Week);
request.setAttribute("dayOfWeekNumber", dayOfWeekNumber);
request.setAttribute("day", day);

String yearParam = request.getParameter("year");
String monthParam = request.getParameter("month");

int selectedYear = (yearParam != null && !yearParam.isEmpty()) ? Integer.parseInt(yearParam) : 0;
int selectedMonth = (monthParam != null && !monthParam.isEmpty()) ? Integer.parseInt(monthParam) : 0;

// Calendar cal = Calendar.getInstance();


int firstDayOfWeek = now.lengthOfMonth();
lastDayOfMonth = now.lengthOfMonth();

//윤년인지 확인
boolean isLeapYear = false;
if ((selectedYear % 4 == 0 && selectedYear % 100 != 0) || selectedYear % 400 == 0) {
    isLeapYear = true;
}

// 선택된 달의 마지막 날짜 계산
switch (selectedMonth) {
    case 2:
        lastDayOfMonth = isLeapYear ? 29 : 28;
        break;
    case 4:
    case 6:
    case 9:
    case 11:
        lastDayOfMonth = 30;
        break;
    default:
        lastDayOfMonth = 31;
}
%>

<!-- 달력 테이블 -->
<h4><a href="?year=<%= selectedYear %>&month=<%= selectedMonth - 1 %>"><<<</a> <%= selectedYear %>, <%= selectedMonth %> <a href="?year=<%= selectedYear %>&month=<%= selectedMonth + 1 %>">>>></a></h4>
YEAR : <input type="text" id="year" size="1px" value="<%= selectedYear %>">
MONTH : <select id="month" onchange="changeMonth()">
    <option value="1" <%= selectedMonth == 1 ? "selected" : "" %>>1월</option>
    <option value="2" <%= selectedMonth == 2 ? "selected" : "" %>>2월</option>
    <option value="3" <%= selectedMonth == 3 ? "selected" : "" %>>3월</option>
    <option value="4" <%= selectedMonth == 4 ? "selected" : "" %>>4월</option>
    <option value="5" <%= selectedMonth == 5 ? "selected" : "" %>>5월</option>
    <option value="6" <%= selectedMonth == 6 ? "selected" : "" %>>6월</option>
    <option value="7" <%= selectedMonth == 7 ? "selected" : "" %>>7월</option>
    <option value="8" <%= selectedMonth == 8 ? "selected" : "" %>>8월</option>
    <option value="9" <%= selectedMonth == 9 ? "selected" : "" %>>9월</option>
    <option value="10" <%= selectedMonth == 10 ? "selected" : "" %>>10월</option>
    <option value="11" <%= selectedMonth == 11 ? "selected" : "" %>>11월</option>
    <option value="12" <%= selectedMonth == 12 ? "selected" : "" %>>12월</option>
</select>
<script>
    function changeMonth() {
        var year = document.getElementById("year").value;
        var month = document.getElementById("month").value;
        window.location.href = "?year=" + year + "&month=" + month;
    }
</script>

<table>
    <tr>
        <th style="color:red">일</th>
        <th>월</th>
        <th>화</th>
        <th>수</th>
        <th>목</th>
        <th>금</th>
        <th style="color:blue">토</th>
    </tr>
    <tr id="date">
<% 
    // 첫 번째 주의 빈 칸을 채우기 위해 루프를 돌립니다.
    for (int i = 1; i < firstDayOfWeek; i++) {
%>
    <td>
    </td>
<%
    }
    
//     DayOfWeek day = (DayOfWeek)request.getAttribute("day");
    // 달력을 출력합니다.
    for ( int i = 1; i <= lastDayOfMonth; i++) {
        // 일요일부터 토요일까지 출력하고, 토요일이면 줄을 바꿉니다.
        if (now.getDayOfWeek().getValue() == 5) {
%>
    </tr>
    <tr>
<%
        }
%>
    <td><%= i %></td>
<%
	now.getDayOfWeek();
    }
%>
    </tr>
</table>
</body>
</html>
