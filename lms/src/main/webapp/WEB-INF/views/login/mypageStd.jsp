<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty sessionScope.message}">
	<span class="text-danger">${sessionScope.message}</span>	
	<c:remove scope="session" var="message"/> 
</c:if>
<table class="table table-bordered">
	<tr>
		<th>아이디</th>
		<td>${stdInfo.stdNo}</td>
	</tr>
	<tr>
		<th>이름</th>
		<td>${stdInfo.stdName}</td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td>${stdInfo.stdTelno}</td>
	</tr>
	<tr> 
		<th>주소</th>
		<td>${stdInfo.stdAddress}</td>
	</tr>
	<tr>
		<th>지도교수</th>
		<td>${stdInfo.proName}</td>
	</tr>

	<tr>
		<td colspan="2">
			<button class="btn btn-danger" id="delBtn">탈퇴</button>
			<a class="btn btn-primary" href='<c:url value = "/staff/staffStudentUpdate.do" />'>개인정보수정</a>
		</td>
	</tr>
</table>