<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table> 
	<tr>
		<td>일련번호</td>
		<td><input type="text" value="${professor.proNo }"/></td>
	</tr>
	<tr>
		<td>교수이름</td>
		<td><input type="text" value="${professor.proName }"/></td>
	</tr>
	<tr>
		<td>전공</td>
		<td><input type="text" value="${professor.proMajor }"/></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><input type="text" value="${professor.proTelno }"/></td>
	</tr>
	<tr>
		<td>교과목코드</td>
		<td><input type="text" value="${professor.subCd } " readonly/></td>
	</tr>
	<tr>
		<td>강의시간</td>
		<td><input type="text" value="${professor.lecTime }" readonly/></td>
	</tr>
	<tr>
		<td>강의실</td>
		<td><input type="text" value="${professor.lecRoom }" readonly/></td>
	</tr>
	<tr>
		<td>강의요일</td>
		<td><input type="text" value="${professor.lecDay }" readonly/></td>
	</tr>
	<tr>
		<td><a class="btn btn-primary" href='<c:url value="/professor/professorUpdate.do" />'>정보수정</a></td>	
	</tr>
</table>
