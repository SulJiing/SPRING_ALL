<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-bordered">
	<tr>
		<th>아이디</th>
		<td>${proInfo.proNo}</td>
	</tr>
	<tr>
		<th>이름</th>
		<td>${proInfo.proName}</td>
	</tr>
	<tr>
		<th>전공</th>
		<td>${proInfo.proMajor}</td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td>${proInfo.proTelno}</td>
	</tr>

		<td colspan="2">
			<button class="btn btn-danger" id="delBtn">탈퇴</button>
			<a class="btn btn-primary" href='<c:url value = "/professor/professorUpdate.do" />'>개인정보수정</a>
		</td>
	</tr>
</table>