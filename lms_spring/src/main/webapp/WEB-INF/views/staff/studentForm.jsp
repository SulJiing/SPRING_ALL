<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
<form:form id="createForm" modelAttribute="student" >
	<ul>
		<li><h6>학&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;번 : &nbsp;</h6><form:input path="stdNo" class="form-control"/><form:errors path="stdNo" element="span" cssClass="text-danger"/></li>
		<li><h6>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름 : &nbsp;</h6><form:input path="stdName" class="form-control"/><form:errors path="stdName" element="span" cssClass="text-danger"/></li>
		<li><h6>전&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;화 : &nbsp;</h6><form:input path="stdTelno" class="form-control"/><form:errors path="stdTelno" element="span" cssClass="text-danger"/></li>
		<li><h6>주&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소 : &nbsp;</h6><form:input path="stdAddress" class="form-control"/><form:errors path="stdAddress" element="span" cssClass="text-danger"/></li>
		<li><h6>주민번호 : &nbsp;</h6><form:input path="stdId" class="form-control"/><form:errors path="stdId" element="span" cssClass="text-danger"/></li>
		<li><h6>지도교수코드 : &nbsp;</h6><form:input path="proNo" class="form-control"/><form:errors path="proNo" element="span" cssClass="text-danger"/></li>
	</ul>
</form:form>
<script type="text/javascript">
$(addBtn).on("click",function(event){
	$(createForm).submit();
});
</script>