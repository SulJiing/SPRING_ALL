<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<style>
.btn{
	margin-left: 10px;
}
#addBtn{
position: absolute;
	right: 30px;
}
.form-control{
	width: 200px;
	margin-left: 10px;
}
#settings{
	display: flex;
	margin-bottom: 20px;
	padding-left: 10px;
}
</style>
<h1>학생 관리/조회</h1>
<hr/>
<form id="searchForm" action="<c:url value ='/staff/staffStudentList.do'/>">
	<input type="hidden" name="page"/> 
	<input type="hidden" name="searchType" value="${condition.searchType }" /> 
	<input type="hidden" name="searchWord" value="${condition.searchWord }" />
</form> 
<div id="settings" data-pg-role="searchUI" data-pg-target="#searchForm">
	<select name="searchType" data-pg-init-value="${condition.searchType }">
		<option value>전체</option>
		<option value="stdNo">학번</option>
		<option value="stdName">이름</option>
		<option value="proName">지도교수</option>
	</select>
	<input type="text" name="searchWord" class="form-control" data-pg-init-value="${condition.searchWord }" />
	<button type="button" data-pg-role="searchBtn" class="btn btn-primary">검색</button>
	<button id="addBtn" class="btn btn-primary">추가</button>
</div>
<table class="table table-bordered">
	<thead>
      <tr>
         <th>일련번호</th>
         <th>학번</th>
         <th>이름</th>
         <th>전화번호</th>         
         <th>지도교수</th>         
      </tr>
   </thead>
   <tbody>
      <c:if test="${not empty studentList }">
         <c:forEach items="${studentList }" var="student">
            <tr>
               <td>${student.rnum }</td>
               <td><a href="javascript:fn_detail('${student.stdNo }');">${student.stdNo }</a></td>
               <td>${student.stdName }</td>
               <td>${student.stdTelno }</td>
               <td>${student.proName }</td>
            </tr>
         </c:forEach>
      </c:if>
      <c:if test="${empty studentList }">
         <tr>
            <td colspan="5">정보 없음.</td>
         </tr>
      </c:if>
   </tbody>
   <tfoot>
      <tr>
         <td colspan="5">
            ${pagingHTML }
         </td>
      </tr>
   </tfoot>
</table>
<form id="detailForm" method="post" action='<c:url value="/staff/staffStudentDetail.do"/>'>
	<input type="hidden" name="stdNo"/>
</form>
<script src="<c:url value='/resources/js/app/common/paging.js' />"></script>
<script type="text/javascript">
function fn_detail(stdNo){
	$(detailForm).find("input").val(stdNo);
	$(detailForm).submit();
}
$(addBtn).on("click",function(){
	location.href="<c:url value ='/staff/staffStudentCreate.do'/>";
});
</script>
