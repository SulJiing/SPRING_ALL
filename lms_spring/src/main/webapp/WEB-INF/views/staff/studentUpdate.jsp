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
#modBtn{
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
	margin-top: 30px;
}
</style>
<h1>${student.stdName } 학생 수정</h1>
<hr/>
<div id="detail">
	<div id="line">
	<h4>학생기본정보</h4>
	<button id="saveBtn" class="btn btn-primary">저장</button>
	</div>
	<form method="post" id="updateForm">
		<ul>
			<li><h6>학&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;번 : &nbsp;</h6><input type="text" name="stdNo" value="${student.stdNo }" readonly class="form-control"/></li>
			<li><h6>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름 : &nbsp;</h6><input type="text" name="stdName" value="${student.stdName }" class="form-control"/></li>
			<li><h6>전&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;화 : &nbsp;</h6><input type="text" name="stdTelno" value="${student.stdTelno }" class="form-control"/></li>
			<li><h6>주&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소 : &nbsp;</h6><input type="text" name="stdAddress" value="${student.stdAddress }" class="form-control"/></li>
			<li><h6>주민번호 : &nbsp;</h6><input type="text" name="stdId" value="${student.stdId }" class="form-control"/></li>
			<li><h6>지도교수코드 : &nbsp;</h6><input type="text" name="proNo" value="${student.proNo }" class="form-control"/></li>
		</ul>
	</form>
</div>
<br/>
<br/>
<div id="clazz">
	<h4>학생수강정보</h4>
	<table class="table table-bordered">
		<tr>
			<th>수강과목</th>
			<th>학점</th>
		</tr>
		<c:if test="${not empty student.clazzList }">
			<c:forEach items="${student.clazzList }" var="clazz">
				<tr>
					<td>${clazz.subject.subName }</td>
					<td>${clazz.clsScore }</td>
				</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${empty student.clazzList }">
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