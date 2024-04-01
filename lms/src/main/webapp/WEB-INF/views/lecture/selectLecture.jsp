<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
	<table class="table table-bordered">
	<thead>
		<tr>
			<th>교수번호</th>
			<th>과목코드</th>
			<th>강의시간</th>
			<th>강의요일</th>
			<th>강의실</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty lecture }">
			<c:forEach items="${lecture}" var="lecture">
				<tr>
					<td>${lecture.proNo}</td>
					<td>${lecture.subCd}<a href="${pageContext.request.contextPath}/professor/studentList.do?subCd=${lecture.subCd}">학생조회</a></td>
					<td>${lecture.lecTime}</td>
					<td>${lecture.lecDay}</td>
					<td>${lecture.lecRoom}</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty lecture }">
			<tr>
				<td colspan="6">강의가 없습니다.</td>
			</tr>
		</c:if>
	</tbody>
</table>
    
   
    
    
    
   <script src="<c:url value ='/resources/js/app/common/paging.js' />"></script>