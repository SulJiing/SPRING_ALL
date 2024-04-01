<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
	<table class="table table-bordered">
	<thead>
		<tr>
			<th>교수번호</th>
			<th>과목코드</th>
			<th>과목명</th>
			<th>강의시간</th>
			<th>강의요일</th>
			<th>강의실</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty lectureList }">
			<c:forEach items="${lectureList }" var="lecture">
				<tr>
					<td>${lecture.proNo }</td>
					<td>${lecture.subCd }</td>
					<td>${lecture.subName }</td>
					<td>${lecture.lecTime }</td>
					<td>${lecture.lecDay }</td>
					<td>${lecture.lecRoom }</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty lectureList }">
			<tr>
				<td colspan="6">강의가 없습니다.</td>
			</tr>
		</c:if>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<form id="searchForm" action="<c:url value = '${pageContext.request.contextPath}/lecture/luectureList.do'/>" />
					<input type="hidden" name="page" />
					<input type ="hidden" name="prodLgu" value="${condition.prodLgu }"/>
					<input type ="hidden" name="prodBuyer" value="${condition.prodBuyer}"/> 
					<input type ="hidden" name="prodName" value="${condition.prodName}"/>
				</form>
				<div data-pg-role="searchUI" data-pg-target="#searchForm">
					<select name="prodLgu" data-pg-init-value="${condition.prodLgu }">
						<option value>교수번호</option>
						<option value>전자제품</option>
						<option value="P101">전자제품</option>
					</select>
					<select name="prodBuyer" data-pg-init-value="${condition.prodBuyer }">
						<option value>과목코드</option>
						<option value="P10101">삼성전자</option>
					</select>
					<input type ="text" name="prodName" value="${condition.prodName}"/>
					<button data-pg-role="searchBtn">검색</button>
				</div> 
				${pagingHTML }
			</td>
		</tr>
	</tfoot>
</table>
    
   
    
    
    
   <script src="<c:url value ='/resources/js/app/common/paging.js' />"></script>