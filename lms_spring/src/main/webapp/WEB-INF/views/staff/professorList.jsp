<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<style>
.searchUI{
	display: flex;
	padding-left: 10px;
}
.form-control{
	width: 200px;
	margin-right: 20px;
}
#addBtn{
	position:absolute;
	right: 30px;
}
</style>
<h1>교수목록</h1>
<div id="items">
<table id="table">
	<thead>
		<tr>
			<th>일련번호</th>
			<th>교수번호</th>
			<th>이름</th>
			<th>전공</th>
			<th>전화번호</th>
		</tr>
	</thead>
	<tbody class="table-group-divider">
		<!-- screan size : 3, block size : 3 -->
		<c:choose>
			<c:when test="${not empty professorList }">
				<c:forEach items="${professorList }" var="professor">
					<tr>
						<td>${professor.rnum }</td>
						<td><a href='<c:url value="/staff/professorView/${professor.proNo }"/>'>${professor.proNo }</a></td>
						<td>${professor.proName }</td>
						<td>${professor.proMajor }</td>
						<td>${professor.proTelno }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="5">조건에 맞는 회원이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="5">
				<form:form id="submitForm" modelAttribute="paging" method="get">
					<input type="hidden" name="page" />
					<form:input type="hidden" path="simpleCondition.searchType"/>
					<form:input type="hidden" path="simpleCondition.searchWord"/>
				</form:form>
				<br/>
				<div class="searchUI" data-pg-role="searchUI" data-pg-target="#submitForm">
					<form:select path="paging.simpleCondition.searchType" class="form-control">
						<form:option value="" label="전체" />
						<form:option value="proName" label="이름" />
						<form:option value="proMajor" label="전공" />
					</form:select>
					<form:input path="paging.simpleCondition.searchWord" class="form-control" />
					<button type="button" data-pg-role="searchBtn" class="btn btn-outline-success" >검색</button>
					<button id="addBtn" class="btn btn-success">추가</button>
				</div>
				<br/>
				${pagingHTML }
			</td>
		</tr>
	</tfoot>
</table>
</div>
<script src='<c:url value="/resources/js/app/common/paging.js" />'></script>
<script type="text/javascript">
	$(addBtn).on("click",()=>{
		document.location.href='<c:url value="/staff/professorForm"/>';
	});
</script>
