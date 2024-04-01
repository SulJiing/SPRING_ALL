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
</style>
<h1>개설강의목록</h1>
<div id="items">
<table id="table">
	<thead>
		<tr>
			<th>일련번호</th>
			<th>교수번호</th>
			<th>교수이름</th>
			<th>교과목코드</th>
			<th>교과목명</th>
			<th>강의시간</th>
			<th>강의실</th>
			<th>강의요일</th>
			<th>#</th>
		</tr>
	</thead>
	<tbody class="table-group-divider">
		<!-- screan size : 3, block size : 3 -->
		<c:choose>
			<c:when test="${not empty lectureList }">
				<c:forEach items="${lectureList }" var="lecture">
					<tr>
						<td>${lecture.rnum }</td>
						<td>${lecture.proNo }</td>
						<td>${lecture.professor.proName }</td>
						<td class="subCd">${lecture.subCd }</td>
						<td>${lecture.subject.subName }</td>
						<td>${lecture.lecTime }</td>
						<td>${lecture.lecRoom }</td>
						<td>${lecture.lecDay }</td>
						<td><button name="submitBtn" type="button" class="btn btn-outline-info">신청</button></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="9">조건에 맞는 회원이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="9">
				<form:form id="submitForm" modelAttribute="paging" method="get">
					<input type="hidden" name="page" />
					<form:input type="hidden" path="simpleCondition.searchType"/>
					<form:input type="hidden" path="simpleCondition.searchWord"/>
				</form:form>
				<br/>
				<div class="searchUI" data-pg-role="searchUI" data-pg-target="#submitForm">
					<form:select path="paging.simpleCondition.searchType" class="form-control">
						<form:option value="" label="전체" />
						<form:option value="proName" label="교수이름" />
						<form:option value="subName" label="과목이름" />
					</form:select>
					<form:input path="paging.simpleCondition.searchWord" class="form-control" />
					<button type="button" data-pg-role="searchBtn" class="btn btn-outline-success">검색</button>
				</div>
				<br/>
				${pagingHTML }
			</td>
		</tr>
	</tfoot>
</table>
</div>
<form id="submitForm" action="?" method="post">
	<input type="hidden" name="subCd">
</form>
<script src='<c:url value="/resources/js/app/common/paging.js" />'></script>
<script type="text/javascript">
	$("[name='submitBtn']").on("click",(event)=>{
		
		var td = $(event.target).closest('td');
		var subCd = td.closest('tr').find('.subCd').text();
		
		$(submitForm).find('input[name="subCd"]').val(subCd);
		$(submitForm).submit();
		
	});
</script>



