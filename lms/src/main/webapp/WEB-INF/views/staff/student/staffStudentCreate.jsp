<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<style>
#addBtn{
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
#createForm{
	margin-top: 20px;
}
</style>
<h1>학생 등록</h1>
<hr/>
<div id="line">
<h4>학생기본정보</h4>
<button id="addBtn" class="btn btn-primary">등록</button>
</div>
<form id="createForm" method="post" action="<c:url value ='/staff/staffStudentCreate.do'/>">
	<ul> 
		<li><h6>학&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;번 : &nbsp;</h6><input type="text" name="stdNo" value="${student.stdNo }" class="form-control"/><span class="text-danger">${errors.stdNo}</span></li>
		<li><h6>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름 : &nbsp;</h6><input type="text" name="stdName" value="${student.stdName }" class="form-control"/><span class="text-danger">${errors.stdName}</span></li>
		<li><h6>전&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;화 : &nbsp;</h6><input type="text" name="stdTelno" value="${student.stdTelno }" class="form-control"/><span class="text-danger">${errors.stdTelno}</span></li>
		<li><h6>주&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소 : &nbsp;</h6><input type="text" name="stdAddress" value="${student.stdAddress }" class="form-control"/><span class="text-danger">${errors.stdAddress}</span></li>
		<li><h6>주민번호 : &nbsp;</h6><input type="text" name="stdId" value="${student.stdId }" class="form-control"/><span class="text-danger">${errors.stdId}</span></li>
		<li><h6>지도교수코드 : &nbsp;</h6><input type="text" name="proNo" value="${student.proNo }" class="form-control"/><span class="text-danger">${errors.proNo}</span></li>
	</ul>
</form>
<script type="text/javascript">
$(addBtn).on("click",function(event){
	$(createForm).submit();
});
</script>