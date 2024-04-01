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
<h1>교수 등록</h1>
<hr/>
<div id="line">
<h4>교수기본정보</h4>
<button id="addBtn" class="btn btn-primary">등록</button>
</div>
<form:form id="createForm" modelAttribute="professor" >
	<ul>
		<li><h6>교수번호 :&nbsp; </h6><form:input path="proNo" class="form-control"/><form:errors path="proNo" element="span" cssClass="text-danger"/></li>
		<li><h6>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름 :&nbsp; </h6><form:input path="proName" class="form-control" /><form:errors path="proName" element="span" cssClass="text-danger"/></li>
		<li><h6>전&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;공 :&nbsp; </h6><form:input path="proMajor" class="form-control" /><form:errors path="proMajor" element="span" cssClass="text-danger"/></li>
		<li><h6>전&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;화 :&nbsp; </h6><form:input path="proTelno" class="form-control" /><form:errors path="proTelno" element="span" cssClass="text-danger"/></li>
	</ul>
</form:form>
<script type="text/javascript">
$(addBtn).on("click",function(event){
	$(createForm).submit();
});
</script>