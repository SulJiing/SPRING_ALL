<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="table table-bordered">
	<thead>
		<tr>
			<td>일련번호</td>
			<td>교수번호</td>
			<td>교수이름</td>
			<td>전공</td>
			<td>전화번호</td>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty professorList }">
			<%-- for( tmp : list ) --%>
			<c:forEach items="${professorList }" var="professor">
				<tr>
					<td>${professor.rnum }</td>
					<td><a href="${pageContext.request.contextPath }/professor/professorSelect.do?proNo=${professor.proNo }">${professor.proNo }</a></td>
					<td>${professor.proName }</td>
					<td>${professor.proMajor }</td>
					<td>${professor.proTelno }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty professorList }">
			<tr>
				<td colspan="6">상품 없음</td>
			</tr>
		</c:if> 
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<form id="searchForm" action="">
					<input type="text" name="page"/> 
					<input type="text" name="searchType" value="${condition.searchType }"/>
					<input type="text" name="searchWord" value="${condition.searchWord }"/>
				</form>
				<div data-pg-role="searchUI" data-pg-target="#searchForm">
					<select name="searchType" data-pg-init-value="${condition.searchType }">
						<option value>전체</option>
						<option value="proName">교수이름</option>
						<option value="proMajor">전공교과목</option>
					</select>
					<input type="text" name="searchWord" data-pg-init-value="${condition.searchWord }"  placeholder="검색어 입력"/>
					<button type="button" data-pg-role="searchBtn">검색</button>
					
					<a class="btn btn-primary" href='<c:url value="/professor/professorInsert.do"/>'>교수추가</a>	
				</div>
				${pagingHTML }
			</td>
		</tr>
	</tfoot>
</table>
<script src="<c:url value='/resources/js/app/common/paging.js'/>"></script>