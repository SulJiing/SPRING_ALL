<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>학점조회</h1>
<div id="items">
	<table id="table">
		<thead>
			<tr>
				<th>과목명</th>
				<th>학점</th>
			</tr>
		</thead>
		<c:if test="${not empty clazzList }">
			<tbody class="table-group-divider">
				<c:forEach items="${clazzList }" var="clazz">
					<tr>
						<td class='subName'>${clazz.subject.subName }</td>
						<td class='clsScore'>${clazz.clsScore }</td>
					</tr>
				</c:forEach>
			</tbody>
		</c:if>
		<c:if test="${empty clazzList }">
			<tbody class="table-group-divider">
				<tr>
					<td colspan="2">아직 수강중인 강의가 없습니다.</td>
				</tr>
			</tbody>
		</c:if>
	</table>
</div>