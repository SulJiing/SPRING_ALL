<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<style>
#clazz{
	width: 1000px;
	margin: 0 auto;
}
#detail{
	width: 1000px;
	margin: 0 auto;
}
#saveBtn{
	margin-left: 20px;
}
#line{
	display: flex; 
	align-items: center;
}
li{
	display: flex; 
}
.form-control{
	width: 200px;
}
#updateForm{
	margin-top: 20px;
} 
</style>
<c:if test="${not empty message }">
	${message }
</c:if>  
<h1>학생 상세페이지</h1>
<hr/>
<div id="detail">
	<div id="line">
	<h4>학생기본정보</h4>
	<button id="saveBtn" class="btn btn-primary">저장</button>
	</div>
	<form id="updateForm" method="post" action="<c:url value ='/staff/staffStudentUpdate.do'/>">
		<ul>
			<li><h6>학&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;번 : &nbsp;</h6><input type="text" name="stdNo" value="${studentMap.stdNo }" readonly class="form-control"/><span class="text-danger">${errors.stdNo}</span></li>
			<li><h6>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름 : &nbsp;</h6><input type="text" name="stdName" value="${studentMap.stdName }" class="form-control"/><span class="text-danger">${errors.stdName}</span></li>
			<li><h6>전&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;화 : &nbsp;</h6><input type="text" name="stdTelno" value="${studentMap.stdTelno }" class="form-control"/><span class="text-danger">${errors.stdTelno}</span></li>
			<li><h6>주&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소 : &nbsp;</h6><input type="text" name="stdAddress" value="${studentMap.stdAddress }" class="form-control"/><span class="text-danger">${errors.stdAddress}</span></li>
			<li><h6>주민번호 : &nbsp;</h6><input type="text" name="stdId" value="${studentMap.stdId }" class="form-control"/><span class="text-danger">${errors.stdId}</span></li>
			<li><h6>지도교수코드 : &nbsp;</h6><input type="text" name="proNo" value="${studentMap.proNo }" class="form-control"/><span class="text-danger">${errors.proNo}</span></li>
		</ul>
	</form>
</div>
<br/>
<div id="clazz">
	<h4>학생수강정보</h4>
	<table class="table table-bordered">
		<tr>
			<th>수강과목</th>
			<th>학점</th>
		</tr>
		<c:if test="${not empty clazzList }">
			<c:forEach items="${clazzList }" var="clazz">
				<tr>
					<td>${clazz.subName }</td>
					<td>${clazz.clsScore }</td>
				</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${empty clazzList }">
			<tr>
	            <td colspan="2">수강 정보 없음.</td>
	         </tr>
		</c:if>
	</table>
</div>
<script type="text/javascript">
$(saveBtn).on("click",function(event){
	$(updateForm).submit();
});
</script>