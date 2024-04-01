<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
	<c:when test="${not empty message}">
		<pre>
		message = ${message }
		member = ${member }
		contentPage = ${contentPage }
		errors = ${errors }
			<c:remove var="message" scope="session"/>
		</pre>
	</c:when>
	<c:otherwise>
		메세지 없음... 
	</c:otherwise>
</c:choose>

<form method="post">
	<table>
		<tr>
			<td>교수번호</td>
			<td>
				<input type="text" name="proNo" class="form-control"/>
				<span class="text-danger">${errors.proNo}</span>
			</td>
		</tr>
		<tr>
			<td>교수이름</td>
			<td>
				<input type="text" name="proName" class="form-control"/>
				<span class="text-danger">${errors.proName}</span>
			</td>
		</tr>
		<tr>
			<td>전공</td>
			<td>
				<input type="text" name="proMajor" class="form-control"/>
				<span class="text-danger">${errors.proMajor}</span>
			</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>
				<input type="text" name="proTelno" class="form-control"/>
				<span class="text-danger">${errors.proTelno}</span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit" class="btn btn-primary">전송</button>
				<button type="reset" class="btn btn-danger">취소</button>
			</td>
		</tr>
	</table>
</form>