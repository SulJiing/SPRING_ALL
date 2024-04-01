<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <h1>수강정보</h1>
    <hr/>
	<table class="table table-bordered">
		<tr>
			<th>학번</th>
			<th>이름</th>
			<th>교과목코드</th>
			<th>교과목명</th>
			<th>학점</th>
		</tr>
		<c:if test="${not empty clazzList }">
			<c:forEach items="${clazzList }" var="clazz">
				<tr>
					<td>${clazz.stdNo }</td>
					<td>${clazz.stdName }</td>
					<td>${clazz.subCd }</td>
					<td>${clazz.subName }</td>
					<td>${clazz.clsScore }</td>
				</tr>
			</c:forEach>
		</c:if>
		 
		<c:if test="${empty clazzList }">
			<tr>
	            <td colspan="5">수강 정보 없음.</td>
	         </tr>
		</c:if>
	</table>