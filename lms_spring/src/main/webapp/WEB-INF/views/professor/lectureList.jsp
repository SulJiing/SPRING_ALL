<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>내강의목록</h1>
<div id="items">
<table id="table">
	 <thead>
	<tr>
		<th>교과목코드</th>
		<th>교과목명</th>
		<th>강의시간</th>
		<th>강의실</th>
		<th>강의요일</th>
	</tr>
	</thead>
	<c:if test="${not empty lectureList }">
		<tbody class="table-group-divider">
		<c:forEach items="${lectureList }" var="lecture">
			<tr>
				<td><a href='<c:url value="lectureListPro/${lecture.subCd }?subName=${lecture.subject.subName }"/>'>${lecture.subCd }</a></td>
				<td>${lecture.subject.subName }</td>
				<td>${lecture.lecTime }</td>
				<td>${lecture.lecRoom }</td>
				<td>${lecture.lecDay }</td>
			</tr>
		</c:forEach>
		</tbody>
	</c:if>
	<c:if test="${ empty lectureList }">
		<tbody class="table-group-divider">
		<tr>
			<td>아직 개설한 강의가 없습니다.</td>
		</tr>
		</tbody>
	</c:if>

</table>
</div>